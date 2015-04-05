package src;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * Coded by             : Jaswant Singh [joney_000]
 * Lang   				: Java
 * Concept 				: Multi threading and Synchronization.
 * Release Date         : 31/march/2015
 * Email 				: jaswantsinghyadav007@gmail.com
 * Class USE            : Extract all the Recors / Emails which we have to send mails from DATABSE
 */

public class RecordsExtractor {
        /*
        * Setting the Basic User Account and Message Detail
        */
        
        // SENDER ADDERESSES , RECEIVER ADDRESS , SUBJECT , BODY of MESSAGE , and USER PASSWORD
	private static ArrayList <String> from = new ArrayList <String>();
	private static ArrayList <String> to = new ArrayList <String>();
        private static ArrayList <String> subject = new ArrayList <String>();
	private static ArrayList <String> body = new ArrayList <String>();
        private static ArrayList <String> pass = new ArrayList <String>();
	
	private static ArrayList<Integer> id = new ArrayList<Integer>() ;
	public Connection connection;  // For Data base Connection
	private int noOfRecords;        //Total Mail Tasks
	
	public RecordsExtractor(ArrayList<Integer> id,ArrayList<String> from,ArrayList<String> pass,ArrayList<String> to,ArrayList<String> subject,ArrayList<String> body,Connection connection){
		// Constructur Assign the  
                this.from=from;
		this.pass=pass;
		this.to=to;
		this.subject=subject;
		this.body=body;
		this.id=id;
		this.connection=connection;
	}
	
	public void addRecords()throws Exception{	
		noOfRecords = 0;
                ResultSet rs = null;
		try
		{
			//take result set from database	
			Statement st = connection.createStatement();
			rs=st.executeQuery("select * from emailqueue e where is_sent =0");
			System.out.println("ADDING RECORDS TO ARRAYLIST");
	
                        while(rs.next()){
                                noOfRecords++;
				id .add(rs.getInt(1));
				from.add(rs.getString(2));
				pass.add(rs.getString(3));
				to.add(rs.getString(4));
				subject.add(rs.getString(5));
				body.add(rs.getString(6));		
			}
			System.out.println("Recors are Added");
	
		}
		catch(SQLException ex){
			 Logger.getLogger(RecordsExtractor.class.getName()).log(Level.SEVERE, null, ex);
		}finally{
                         
                    connection.close();
                }
		
	}
        
	//give the total count of records
	public int countRecords(){
            
		return noOfRecords;
	}
	
}