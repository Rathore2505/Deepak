def call(from , subject)
	{
	string text = readFile 'C:\\Program Files (x86)\\Jenkins\\Text.txt'
	String[] lines = text.text.split('\n')
        for(int i =1; i<lines.size(); i++)
           {
	     mail bcc: '', body: 'Testing', cc: '', from: from , replyTo: '', 
             subject: subject, to: [i]
		   println "Mail ID : " + i
	   }
	}
