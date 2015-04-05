package TestPackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import src.ConnectionProvider;
import src.ConnectionProvider;
/*
 * Coded by             : Jaswant Singh [joney_000]
 * Lang   				: Java
 * Concept 				: Multi threading and Synchronization.
 * Release Date         : 31/march/2015
 * Email 				: jaswantsinghyadav007@gmail.com
 * Description          : This class adds 400 Records in Database [Email-Queue Table]
 */

public class InsertRecords{
        // EDIT THE NO of RECORS ACCORDING YOUR REQUIRMENT
        private static final short noBaseRecords = 400;  //Total Records
        private static final short noOfRecords = 5;
        private static final String defaultPass = "9413236"; //PLEASE EDIT THE PASSWOD
        //Gernal User Account Details 
        // SENDER ADDERESSES , RECEIVER ADDRESS , SUBJECT , BODY of MESSAGE , and USER PASSWORD
        private static ArrayList <String> from = new ArrayList <String>();
	private static ArrayList <String> to = new ArrayList <String>();
        private static ArrayList <String> subject = new ArrayList <String>();
	private static ArrayList <String> body = new ArrayList <String>();
        private static ArrayList <String> pass = new ArrayList <String>();
	//Function add 400 addresses	
	// 	
	  public static void addRandomAddresses()throws java.lang.Exception{
              
                   Connection connection=null;
                   ConnectionProvider cp  = new ConnectionProvider();
                   cp.initialize();
		try{
                    for(int i=0;i<noBaseRecords;i++){	
                               
                                connection  = cp.getConnection();  // MAKE THE CONNECTION
                                Statement st= connection.createStatement();
                                // Pick the random row of Fixed 5 address	
                                Random rand = new Random();
				int index= Math.abs(rand.nextInt()+i)%5;
                                // add records in Table 
                                // Quesry to insert a Row In EMAIL-QUEUE TABLE
				st.executeUpdate("insert into emailqueue(from_email_address,password,to_email_address,subject,body) values('"+from.get(index)+"','"+pass.get(index)+"','"+to.get(index)+"','"+subject.get(index)+"','"+body.get(index)+"')");
                                connection.close();
                                System.out.println("Record Inserted");
			}
		}catch(SQLException ex)
		{
			System.out.println("Invalid sql Exception:"+ex.getMessage().toUpperCase());
		}catch(Exception ex)
		{
			System.out.println("error:"+ex.toString());
		}finally{
                    //close the connection
                  //
                }
  }
	public static void main (String []s)throws Exception{
		
		/**
                 * 
                 * These are Temporary Examples
                 * 
                 * Please Take Some Valid UserName and Password
                 * for that change Default Password with SENDER's VALID PASSWORD.
                 * 
                 */
		
		//values details one     //USER 1
		from.add("jaswantsinghyadav007@gmail.com");   // SENDER   
		pass.add(defaultPass);                          
		to.add("afjal321@gmail.com");                  //RECEIVER
		subject.add("GMAIL SMTP STRESS TEST");
		body.add("Hello Afjal");                        //MESSAGE
		
                //values details one     //USER 2
		from.add("rahulanandsinha@gmail.com");
		pass.add(defaultPass);
		to.add("jaswantsinghyadav8989@gmail.com");
		subject.add("GMAIL SMTP STRESS TEST");
		body.add("Hello Jaswant");
		
                //values details one     //USER 3
		from.add("jaswantsinghyadav8989@gmail.com");
		pass.add(defaultPass);
		to.add("afjal321@gmail.com");
		subject.add("GMAIL SMTP STRESS TEST");
		body.add("Hello Afjal");
		
                //values details one     //USER 4
		from.add("keshav1.laad@gmail.com");
		pass.add(defaultPass);
		to.add("jaswantsinghyadav8989@gmail.com");
		subject.add("GMAIL SMTP STRESS TEST");
		body.add("Hello Jawant");
		
                  //values details one     //USER 5
		from.add("shubhamtaliwal23@gmail.com");
		pass.add(defaultPass);
		to.add("dharmvrbaloda836@gmail.com");
		subject.add("GMAIL SMTP STRESS TEST");
		body.add("Hello Dharmveer !");
		
                addRandomAddresses();
                //function adds 400 records randomly;
	}


  }
