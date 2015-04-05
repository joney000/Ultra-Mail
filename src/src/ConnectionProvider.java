
package src;

import java.sql.*;
/*
 * Coded by             : Jaswant Singh [joney_000]
 * Lang   				: Java
 * Concept 				: Multi threading and Synchronization.
 * Release Date         : 31/march/2015
 * Email 				: jaswantsinghyadav007@gmail.com
 *
 */
public class ConnectionProvider 
{
    public  Connection connection;
    
  /*  
   *  DB         : ultamail
   *  table      : Emailqueue
   *  username   : root (Modify according your preferecnce)
   *  password   : root  
   */
        
    public  void initialize(){	
		/*
                 *   This Class Uses For Making A Connection using jdbc.Driver
                 *   
                 *   Initialize function make new Connection to DataBase
                 */
                try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ultramail","root","root");
		}
		catch(ClassNotFoundException ex)    // Exception Handling
		{
			System.out.println("Driver class Not Found :: Invalid driver");
		}catch(SQLException ex)              // Exception Handling
		{
			System.out.println("Invalid sql:"+ex.getMessage().toUpperCase());
		}catch(Exception ex)
		{                                   //if EXCEPTION not caught in upper cases [The Default Handler]
			System.out.println("error:"+ex.getMessage().toUpperCase());
		}
        }
    public  Connection getConnection(){
      
        initialize();
        
        return connection;
    }
}