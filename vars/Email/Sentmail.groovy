package Email


def call(from , to , subject)

	{
	mail bcc: '', body: 'Testing', cc: '', from: from , replyTo: '', 
       subject: subject, to: to
	}
