def call(from , subject)
	{
	string Textpath = readFile 'C:\\Program Files (x86)\\Jenkins\\Text.txt'
	 String[] lines = Textpath.split('\n')
         println "Total lines" + Textpath
	}
