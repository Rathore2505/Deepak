import javax.mail.*
import javax.mail.internet.*
import hudson.model.*;
import jenkins.model.*;
import hudson.tools.*;
import hudson.util.Secret; 

def call(host, sender, receivers, subject, text) 
{
    // Variables
def SystemAdminMailAddress = env['deepak.kumar@ravsoftsolutions.com']
def SMTPUser = env['deepak.kumar@ravsoftsolutions.com']
def SMTPPassword = env['@deepak2505']
def SMTPPort = env['465']
def SMTPHost = env['secure200.inmotionhosting.com']

// Constants
def instance = Jenkins.getInstance()
def mailServer = instance.getDescriptor("hudson.tasks.Mailer")
def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
def extmailServer = instance.getDescriptor("hudson.plugins.emailext.ExtendedEmailPublisher")

Thread.start
    {
    sleep 10000

        //Jenkins Location
        println "--> Configuring JenkinsLocation"
        jenkinsLocationConfiguration.setAdminAddress(SystemAdminMailAddress)
        jenkinsLocationConfiguration.save()

        //E-mail Server
        mailServer.setSmtpAuth(SMTPUser, SMTPPassword)
        mailServer.setSmtpHost(SMTPHost)
        mailServer.setSmtpPort(SMTPPort)
        mailServer.setCharset("UTF-8")

        //Extended-Email
        extmailServer.smtpAuthUsername=SMTPUser
        extmailServer.smtpAuthPassword=Secret.fromString(SMTPPassword)
        extmailServer.smtpHost=SMTPHost
        extmailServer.smtpPort=SMTPPort
        extmailServer.charset="UTF-8"
        //extmailServer.defaultSubject="\$PROJECT_NAME - Build # \$BUILD_NUMBER - \$BUILD_STATUS!"
        //extmailServer.defaultBody="\$PROJECT_NAME - Build # \$BUILD_NUMBER - \$BUILD_STATUS:\n\nCheck console output at \$BUILD_URL to view the results."

        // Save the state
        instance.save()
   } 
    Properties props = System.getProperties()
    props.put("mail.smtp.host", SMTPHost)
    Session session = Session.getDefaultInstance(props, null)

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
