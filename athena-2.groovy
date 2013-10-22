/*
* Larry Eisenstein
* Athena Dictionary Word Value
* 10/20/2013
* 
*/
//import java.utils.Arrays;

class  AthenaWord {
	def startTime
	def endTime
	String word
	int numberOfChars
	char[] sortedWord
	int position
	int finalPosition
	String finalOutput
	
	// used to break out of recursion once answer is found
	boolean kill

public static void main(String[] args) {

	if(args) {
		AthenaWord aw = new AthenaWord(args[0]);
	}
	else {
		// Hard code if you want to use Groovy Web Console: http://groovyconsole.appspot.com/
		AthenaWord aw = new AthenaWord("question");
	}
}

public AthenaWord(String arg) {

	startTime = System.currentTimeMillis()
	kill = false
	position = 0
	finalPosition = 0
	finalOutput = ""
	// Get word from command line
	word = arg
	word = word.toUpperCase()

	if(word.length() < 1 || word.length() > 25)
	{
		throw new ArrayIndexOutOfBoundsException()
	}
	numberOfChars = word.length()
	sortedWord = word.toCharArray()
	Arrays.sort(sortedWord)

	permute(sortedWord.toString())



	endTime = System.currentTimeMillis()
	print "Total Time: " 
	println endTime - startTime + ' ms'
	
}



	

	
	
public void permute( String input)
	{
	  int inputLength = input.length();
	  //System.out.println(inputLength);
	  boolean[ ] used = new boolean[ inputLength ];
	  StringBuffer outputString = new StringBuffer();
	  char[ ] in2 = input.toCharArray( );
	  
	  doPermute ( in2, outputString, used, inputLength, 0 );
	
	}

public void doPermute ( char[ ] in2, StringBuffer outputString, 
                    boolean[ ] used, int inputLength, int level)
  {
  	if(kill)
  		return
     if( level == inputLength) {
     	 position++;

	     if(outputString.toString() == word) {
	     	// matched
	     	finalPosition = position;
	     	System.out.println ( "FINAL POSITION: " + finalPosition);
   		     // Set KILL variable
		     kill = true 
	     }
	     //System.out.println ( outputString.toString()); 
	     finalOutput = outputString.toString();

	     return;
     }

    for( int i = 0; i < inputLength; ++i )
    {       

       if( used[i] ) continue;

       outputString.append( in2[i] );      
       used[i] = true;       
       doPermute( in2,   outputString, used, inputLength, level + 1 );       
       used[i] = false;       
       outputString.setLength(   outputString.length() - 1 );   
    }
 }



}