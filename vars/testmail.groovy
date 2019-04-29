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
def SMTPPort = '587'
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
 
    Properties props = System.getProperties()
    props.put("mail.smtp.host", 'secure200.inmotionhosting.com')
    props.put("mail.smtp.port", '587');
     props.put("mail.smtp.auth" , true);
    //Session session = Session.getDefaultInstance(props,  new javax.mail.Authenticator())
    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
    {
     protected PasswordAuthentication getPasswordAuthentication() 
     {
            return new PasswordAuthentication(SMTPUser, SMTPPassword);
     }
    });
    MimeMessage message = new MimeMessage(session)
    message.setFrom(new InternetAddress(sender))
    receivers.split(',').each 
    {
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(it))    
    }
    message.setSubject(subject)
    message.setText(text)
    println 'Sending mail to ' + receivers + '.'
    Transport.send(message)
    println 'Mail sent.'
        
   
    
}
