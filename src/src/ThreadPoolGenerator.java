package src;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Coded by             : Jaswant Singh [joney_000]
 * Lang   				: Java
 * Concept 				: Multi threading and Synchronization.
 * Release Date         : 31/march/2015
 * Email 				: jaswantsinghyadav007@gmail.com
 * Class USE            : Generate Require No of Threades and execute them
 */



public class ThreadPoolGenerator{
       /*
        * Setting the Basic User Account and Message Detail
        */
        
        // SENDER ADDERESSES , RECEIVER ADDRESS , SUBJECT , BODY of MESSAGE , and USER PASSWORD
         
	private static ArrayList<Integer> id = new ArrayList<Integer>() ;
	private static ArrayList <String> from = new ArrayList <String>();
	private static ArrayList <String> to = new ArrayList <String>();
        private static ArrayList <String> subject = new ArrayList <String>();
	private static ArrayList <String> body = new ArrayList <String>();
        private static ArrayList <String> pass = new ArrayList <String>();
	private static Connection connection;  // For Data base Connection
	private static int noOfRecords;    //Total Mail Tasks
	private static Statement statement; // SQL Rtatement 

    public static void main(String[] args)throws Exception{
		
		//taking a database connection from ConnectionProvider 
                ConnectionProvider cp = new ConnectionProvider();
		cp.initialize();
		connection=cp.getConnection();      //get dB connection
                
		try{    
                    statement = connection.createStatement();   // create SQL statement
                }catch(SQLException ex){} //NO OPERATION
		
                // Extract All Records from Waitin EMAIL-QUEUE TABLE
		RecordsExtractor pool =new RecordsExtractor(id,from,pass,to,subject,body,connection);
		pool.countRecords();  // taking the no of mail/ tasks to send
		noOfRecords=pool.countRecords();
		
                ExecutorService executor = Executors.newFixedThreadPool(noOfRecords);
                 // for Pooling an
                 for(int i = 0; i < noOfRecords; i++){
        	
        	// We assign a mail to a Single thread
                // So all thread are run in parallel
                 // initialize Executer   
                 Runnable exeThread = new ThreadExecuter(i,id.get(i),from.get(i),pass.get(i),to.get(i),subject.get(i),body.get(i),statement);
                 executor.execute(exeThread);       //execute the current thread
        }
        
		executor.shutdown();
                System.out.println("waiting");
        while (!executor.isTerminated()) 
        {
                   // Wait for Terminate the executior
                   // No opration Wait :: Buzy CPU 
        }
        System.out.println("All the Threads in the Pool are Finished :");
        
        System.out.println("Task Completed");
    }
}