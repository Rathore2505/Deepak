import hudson.model.*;
import jenkins.model.*;
import hudson.tools.*;
import hudson.util.Secret;

def call(sender , receiver)
{
// Variables
def SystemAdminMailAddress = "deepak.kumar@ravsoftsolutions.com"
def SMTPUser = "deepak.kumar@ravsoftsolutions.com"
def SMTPPassword = "@deepak2505']
def SMTPPort = "465"
def SMTPHost = "secure200.inmotionhosting.com"

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
        //extmailServer.smtpAuthUsername=SMTPUser
        //extmailServer.smtpAuthPassword=Secret.fromString(SMTPPassword)
        //extmailServer.smtpHost=SMTPHost
        //extmailServer.smtpPort=SMTPPort
        //extmailServer.charset="UTF-8"
        //extmailServer.defaultSubject="\$PROJECT_NAME - Build # \$BUILD_NUMBER - \$BUILD_STATUS!"
        //extmailServer.defaultBody="\$PROJECT_NAME - Build # \$BUILD_NUMBER - \$BUILD_STATUS:\n\nCheck console output at \$BUILD_URL to view the results."

    // Save the state
        instance.save()
      
        // Send mail
        mail bcc: '', body: 'Testing', cc: '', from: from, replyTo: '', 
            subject: 'Jenkins', to: sender
        println "Mail Sent To "+ sender +" :"
} 
   
}
