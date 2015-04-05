package src;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/*
 * Coded by             : Jaswant Singh [joney_000]
 * Lang   				: Java
 * Concept 				: Multi threading and Synchronization.
 * Release Date         : 31/march/2015
 * Email 				: jaswantsinghyadav007@gmail.com
 * Class USE            : extends Authenticator class to Authenticate Particular User it returns PasswordAuthentication Object.
 */


public class MailAuthorizer extends Authenticator {
          
        // SENDER ADDERESSES , RECEIVER ADDRESS , SUBJECT , BODY of MESSAGE , and USER PASSWORD
    String userName;
    String passWord;
    // User Details
    public MailAuthorizer (String username, String password)
    {
       super();
       this.userName = username;
       this.passWord = password;
    }
    // Simply Create an PasswordAuthentication object with id and pass 
    // return it
    
	@Override
	public PasswordAuthentication getPasswordAuthentication()
	{
	    return new PasswordAuthentication(userName, passWord);
	}
}