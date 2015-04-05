# Ultra-Mail
								
***************************************    Ultra Mail    *******************************************
							
		Ultra Mail is an Advance Java Application for large scale mail transfer.
		it provides fast efficient mail transfer in SMTP protocol.Single user can mail to multiple destination.  

		[Key Features]
		
		1.) Parallel Execution by taking advantage of Multi threaded Environment.
		2.) Synchronization and Bandwidth Utilization.
		3.) Scalability and Asynchronous mail Delivery.
		4.) Dynamic Load Management.
		5.) Prevents Duplicate mail sending.
		
	    Project Structure :
		
		Ultra Mail[project_name]
		       |
			   |--Meta Files
			   |--Source Package
			   |		|
			   |		|--Source Code
			   |		|--Test Package
			   |
			   |--Libraries
		
	Deployment Procedure : 
			
			1.) Make Sure the JDK is available to the System.
			2.) Download, extract and open project in NetBeans.
			3.) Make sure you have following Empty files at Ultra Mail/Meta File 
								   a.)javamail.default.address.map
								   b.)javamail.default.providers	
			4.) if database does not have table 'Email-queue' create it by 'query.sql file'
			5.) Write the you Gmail Account name and Password in \source packages\src\ThreadRunner
			6.) connection to the MYSQL database having table Email-queue and mention the database name and password in \source packages\src\Connection.java file.
			7.) driver used------>
					a.)Connector/Jar
					b.)java mail smtp 1.4 vendor=Sun Microsystems, Inc.
					c.)activation.jar
					d.)mysql connector java 5.1 jar. vendor=Sun Microsystems, Inc.
			8.) Clean and Build Project in NetBeans.
			9.) Testing of Working.{ Testing.text  :==> Testing Details}
 			
		
		***	To run the project from the command line, go to the dist folder and
			type the following:

			java -jar "UltraMail.jar" 

/*
 * Released by    : Jaswant Singh [joney_000]
 * Lang   	    	: Java
 * Concept 		    : Multi threading and Synchronization.
 * Release Date   : 29/march/2015
 * Email 		      : jaswantsinghyadav007@gmail.com
 *
 */ 
