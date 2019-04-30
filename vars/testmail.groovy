import javax.mail.*
import javax.mail.internet.*
import hudson.model.*;
import jenkins.model.*;
import hudson.tools.*;
import hudson.util.Secret; 

def call(receivers, subject, text) 
{

    // Variables
def SystemAdminMailAddress = 'admin@merge.com'
def SMTPPort = '25'
def SMTPHost = 'mail.products.network.internal'

// Constants
def instance = Jenkins.getInstance()
def mailServer = instance.getDescriptor("hudson.tasks.Mailer")
def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
//def extmailServer = instance.getDescriptor("hudson.plugins.emailext.ExtendedEmailPublisher")
   
        //Jenkins Location
        println "--> Configuring JenkinsLocation"
        jenkinsLocationConfiguration.setAdminAddress(SystemAdminMailAddress)
        jenkinsLocationConfiguration.save()
        //E-mail Server
        mailServer.setSmtpHost(SMTPHost)
        mailServer.setSmtpPort(SMTPPort)
        mailServer.setCharset("UTF-8")
        // Save the state
        instance.save() 
    
    Properties props = new Properties();
    //props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", SMTPHost);
    //props.put("mail.smtp.user", SMTPUser);
    props.put("mail.smtp.port", SMTPPort);
    //props.put("mail.smtp.password", SMTPPassword);

    Session session = Session.getInstance(props, null);
   
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SystemAdminMailAddress));
        //InternetAddress addressTo = new InternetAddress[receivers];
       // addressTo[i] = new InternetAddress(addressTo);
        message.setRecipients(Message.RecipientType.TO, receivers);
        message.setSubject(subject);
        message.setText(text);
        println 'Sending mail to ' + receivers + '.'
        Transport.send(message);
        println 'Mail sent.'
   
}
   
        
   
    

