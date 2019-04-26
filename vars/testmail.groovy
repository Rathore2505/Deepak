def call(from , subject)
	{
	string Textpath = readFile 'C:\\Program Files (x86)\\Jenkins\\Text.txt'
	 String[] lines = Textpath.split('\n')
	for(int i = 0; i < lines.size(); i++)
           {
	     //mail bcc: '', body: 'Testing', cc: '', from: from , replyTo: '', 
             //subject: subject, to: lines[i]
		   //println "Mail ID : " + lines[i]
		   println "text in line "+i.toString()+" : "+ lines[i]
	   }	
         println "Total lines" + Textpath
	}
