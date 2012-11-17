/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaMail;

/**
 *
 * @author kejun
 */
import javax.mail.*;

public class MyAuthenticator extends Authenticator{
    String userName=null;
    String password=null;

    public MyAuthenticator(){
    }
    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName, password);
    }
}

