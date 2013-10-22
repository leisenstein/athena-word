/*
* Larry Eisenstein
* Athena Dictionary Word Value
* 10/20/2013
* 
*/
// import java.utils.Arrays;

class  AthenaWord {
	def startTime
	def endTime
	String word
	
	
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
		// Get word from command line
		word = arg
		word = word.toUpperCase()

		if(word.length() < 1 || word.length() > 25)
		{
			throw new ArrayIndexOutOfBoundsException()
		}
		

		BigInteger finalScore = new BigInteger(0)
		finalScore = GetScore(word)
		println finalScore



		endTime = System.currentTimeMillis()
		print "Total Time: " 
		println endTime - startTime + ' ms'
		
	}


	public BigInteger GetScore(String w) {
		BigInteger answer = new BigInteger(0)
		BigInteger offset = new BigInteger(0)
		BigInteger totalPermutations = new BigInteger(0)
		ArrayList reverseArrayList = new ArrayList()

		String reverseSortedWord = ReverseSortedWord(word)
		totalPermutations = BigFactorial(word.size())


		reverseSortedWord.each {
			reverseArrayList.add(it)
		}

		w.each {
			BigInteger positionInReverseList = reverseArrayList.indexOf(it)
			BigInteger positionInWordLess1 = w.size() - w.indexOf(it) - 1
			BigInteger posFactorial = new BigInteger(0)
			posFactorial = BigFactorial(positionInWordLess1)
			offset += positionInReverseList.multiply(posFactorial)
			reverseArrayList.remove(it)
		}

		answer = totalPermutations.minus(offset)

		return answer
	}


	public BigInteger BigFactorial(BigInteger n) { 
		BigInteger b = new BigInteger(1);
		for(int i = n; i> 1; i--) {
			BigInteger bi = new BigInteger(i)
			b = b.multiply(bi);
		}
		return b
	}

	public String ReverseSortedWord(String w) {
		// First, Alphabetically Sorted
		String s = SortString(w)

		// Then, Reverse it
		String reverse = new StringBuilder(s).reverse().toString();
		return reverse
	}

	public String SortString(String a) {
		char[] ar = a.toCharArray();
		Arrays.sort(ar);
		String sorted = String.valueOf(ar);
		return sorted
	}
}