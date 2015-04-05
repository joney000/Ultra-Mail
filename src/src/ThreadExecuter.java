package src;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.sql.SQLException;
import java.sql.Statement;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Address;

/*
 * Coded by             : Jaswant Singh [joney_000]
 * Lang   				: Java
 * Concept 				: Multi threading and Synchronization.
 * Release Date         : 31/march/2015
 * Email 				: jaswantsinghyadav007@gmail.com
 * Class USE            : Implementing a Simple Thread and SMTP PROPERTIES to Send Mails.
 */

public class ThreadExecuter implements Runnable 
{     
        private final String threadCommand;
        private static int sendMailcount;
        /*
         *
         *  The Class is Used to Execute the Thread from ThreadPool
         *  It Setup basic Property for Mail like SMTP PORT , USERNAME , MESSAGE , PASSWORD
         *  If There is a blocked Thread it Try Again to Execute : Increase Re  
        */
        

        private final int id;
	final private String from;
	final private String password;
	final private String to;	
	final private String subject;
	final private String body;
	
	final private Statement statement;
	
    public ThreadExecuter(int s,int id,String from,String password,String to,String subject,String body,Statement statement){
        /*
        * Setting the Basic User Account and Message Detail
        */
        
        this.id=id;
        this.from=from;  
        this.password=password;    
        this.to=to;
        this.subject=subject;
        this.body=body;
        this.threadCommand = (""+s+"");  //Convert an Integer to String
        this.statement=statement;        // SQL Statement
    }
    
    private void executeThreadCommand(int countTry){
        
        // If There is Exception/Bokage in Excecution of a Thread 
        //Action ==>> Put it into SLEEP
        try{
                Thread.sleep(150);  
        }
        catch (InterruptedException e){    
            e.printStackTrace(); // Exception Handling
        }
    }
    
 
    
    @Override
    public void run(){
        
        // By default RUN method exceted when a new Thread is Created.     
        // Setting the Mail Properties SETUP  
        String host = "smtp.gmail.com";
        Properties mailProperties = System.getProperties();                 
         mailProperties.put("mail.smtp.starttls.enable", "true");          
       // If true, enables the use of the STARTTLS command (if supported by the server) to switch the connection
       // to a TLS-protected connection before issuing any login commands.
       // Note that an appropriate trust store must configured so that the client will trust the server's certificate.
       // Defaults to false. 
        mailProperties.put("mail.smtp.host", host);
        mailProperties.put("mail.smtp.user", from);
        mailProperties.put("mail.smtp.password", password);
        mailProperties.put("mail.smtp.port", "587");
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.debug", "true");    //to debug the issues

       //The SMTP protocol provider supports the following properties, which we have set in the JavaMail Session object.
       //The properties are always set as strings;
       //the Type column describes how the string is interpreted.
      
        Session session = Session.getInstance(mailProperties, new MailAuthorizer(from, password));
        boolean sent=false;
        int countTry = 0;
        while(sent==false){
            
        try{
        
                 MimeMessage message = new MimeMessage(session);
                 Address fromAddress=new InternetAddress(from);
                 Address toAddress[] = {   
                        new InternetAddress(to)
                 };
                 
             
                 message.setFrom(fromAddress);                              //add sender's addresses
                 message.setRecipients(Message.RecipientType.TO,toAddress); //add receiver's address
                 message.setSubject(subject);                               //add Subject 
                 Transport transport = session.getTransport("smtp");         //setup Transport Protocol = SMTP
                 transport.connect(host, from, password); //time taking 
                 message.setText(body);                                     //set the Body Content
               	 message.saveChanges();   
               	 transport.sendMessage(message, toAddress);                 // Sending the Message
                 transport.close();
                 sent=true;	                                             // mark the sent flag = true
                 System.out.println(  sendMailcount++);
                 int rs=statement.executeUpdate("update emailqueue set is_sent=1 where id="+id);
                                                                              //mark the sent flag in the DataBase = true
                 if(rs>0) System.out.println("record success updated");               //printing success
                 else System.out.println("not inserted in DATABSE");
                
                 System.out.println(rs);
                

		}catch (MessagingException e){
				// If There is Excetion or Blocking of a perticular Thread 
                        executeThreadCommand(++countTry);// put it on sleep and increment the count
                
                }catch(SQLException ex){
        		ex.printStackTrace();
        	}
        }
        String name =  Thread.currentThread().getName();
        System.out.println("thread "+name+" Completed"); //execution Finishes
    }
 
 @Override
    public String toString()
	{
        return this.threadCommand;
    }
    
}