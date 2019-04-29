import javax.mail.*
import javax.mail.internet.*
import hudson.model.*;
import jenkins.model.*;
import hudson.tools.*;
import hudson.util.Secret; 

def call(sender, receivers, subject, text) 
{
    // Variables
def SystemAdminMailAddress = 'deepak.kumar@ravsoftsolutions.com'
def SMTPUser = 'deepak.kumar@ravsoftsolutions.com'
def SMTPPassword = '@deepak2505'
def SMTPPort = '465'
def SMTPHost = 'secure200.inmotionhosting.com'

// Constants
def instance = Jenkins.getInstance()
def mailServer = instance.getDescriptor("hudson.tasks.Mailer")
def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
def extmailServer = instance.getDescriptor("hudson.plugins.emailext.ExtendedEmailPublisher")
        //Jenkins Location
        println "--> Configuring JenkinsLocation"
        jenkinsLocationConfiguration.setAdminAddress(SystemAdminMailAddress)
        jenkinsLocationConfiguration.save()
       
        //E-mail Server
        mailServer.setSmtpAuth(SMTPUser, SMTPPassword)
        mailServer.setSmtpHost(SMTPHost)
        mailServer.setSmtpPort(SMTPPort)
        mailServer.setCharset("UTF-8")
     
        // Save the state
        instance.save() 
 
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", SMTPHost);
    props.put("mail.smtp.user", SMTPUser);
    props.put("mail.smtp.port", SMTPPort);
    props.put("mail.smtp.password", SMTPPassword);

    Session session = Session.getInstance(props, null);
    //try
    //{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        //InternetAddress addressTo = new InternetAddress[receivers];
       // addressTo[i] = new InternetAddress(addressTo);
        message.setRecipients(Message.RecipientType.receivers, receivers);
        message.setSubject(subject);
        message.setText(text);
         println 'Sending mail to ' + receivers + '.'
        Transport.send(message);
        println 'Mail sent.'
   // } 
    //catch (MessagingException e) 
    //{
       //e.printStackTrace();
       // return false;
    //}
    //return true;
}
   
        
   
    

