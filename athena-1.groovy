/*
* Larry Eisenstein
* Athena Dictionary Word Value
* 10/11/2013
* 
*/




def startTime = System.currentTimeMillis()
String word

// Get word from command line
word = args[0]
// String word = "ABC"; // Hard code if you want to use Groovy Web Console: http://groovyconsole.appspot.com/
word = word.toUpperCase()

if(word.length() < 1 || word.length() > 25)
{
	throw new ArrayIndexOutOfBoundsException()
}
int numberOfChars = word.length()

// initialize the array to store the binary values
String[] binaryArray = [ 	  "00000", "00000", "00000", "00000", "00000", "00000", "00000", "00000", "00000", "00000"
							, "00000", "00000", "00000", "00000", "00000", "00000", "00000", "00000", "00000", "00000"
							, "00000", "00000", "00000", "00000", "00000" ] as String[]

// println 'The word is:  ' + word
// println 'It has ' + numberOfChars + ' characters'



int i = 1
word.each {
	// foreach char in the word
	String paddedBinaryValues

	// Convert the character to its ASCII value and let 'A' start from 1 by subtracting 64
	int charWord = (int)it
	charWord = charWord - 64

	// Pad the binary string value with 0's if needed, Groovy way
	paddedBinaryValues = Integer.toBinaryString(charWord).padLeft(5, '0')
	// paddedBinaryValues = String.format("%5s", Integer.toBinaryString(charWord)).replace(' ', '0'); // Java way

	// Store the value in the array
	binaryArray[i-1] = paddedBinaryValues
	i++
};

// Combine all the strings in the array to 1 string
String totalBinaryValue = binaryArray.join("")


// Convert the binary string from Base 2 to a BigInteger, Base 10
BigInteger initialValue = new BigInteger(totalBinaryValue, 2)

// Try to normalize the number a little
BigInteger finalValue = initialValue - 1329227995784915872903807060280344575
// println finalValue

// Convert finalValue into more readable string
int eValue = finalValue.toString().length() - 1
String finalSciNotation = finalValue.toString()[0]  
if(finalValue.toString().length() > 1) {
	finalSciNotation +=  '.' + finalValue.toString()[1..-1] 
	finalSciNotation += ' x 10^' + eValue
} 

println "The value for " + word + " : " + finalSciNotation

// Calculate execution time
def endTime = System.currentTimeMillis()
print "Total Time: " 
println endTime - startTime + ' ms'
