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
	BigInteger totalPermutations
	BigInteger finalOffset
	ArrayList reverseArrayList = new ArrayList()	
	public static void main(String[] args) {

		if(args) {
			AthenaWord aw = new AthenaWord(args[0]);
		}
		else {
			// Hard code if you want to use Groovy Web Console: http://groovyconsole.appspot.com/
			AthenaWord aw = new AthenaWord("BOOKKEEPER");
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
		println "FINAL RANK :::::: " + finalScore



		endTime = System.currentTimeMillis()
		print "Total Time: " 
		println endTime - startTime + ' ms'
		
	}


	public BigInteger GetScore(String w) {
		BigInteger answer = new BigInteger(0)
		finalOffset = new BigInteger(0)
		String reverseSortedWord = ReverseSortedWord(word)
		reverseArrayList = new ArrayList()
		totalPermutations = new BigInteger(0)
		totalPermutations = GetTotalPermutations(word)
		reverseSortedWord.each {
			reverseArrayList.add(it)
		}

		PERMUTEIT(w)
		// println "T: " + totalPermutations
		// println "f: " + finalOffset
		answer = totalPermutations-finalOffset
		return answer
	}



	public BigInteger PERMUTEIT(String p) {
		BigInteger coefficient = new BigInteger(0);
		BigDecimal perms = new BigInteger(0);
		BigInteger currentValue = new BigInteger(0);
		//println "permute:: " + p

		if(p.size() == 1) {
			return 0;
		} else {
			// calculate coefficient
			coefficient = reverseArrayList.indexOf(p[0])
			// println "coeff :: " + coefficient
			reverseArrayList.remove(p[0])
			// calculate # permutations
			perms = GetNextItemPermutations(p)
			// println "perms:::: " + perms
			// multiply them together
			currentValue = coefficient.multiply(perms).toBigInteger()
			// println "currentValue :::::: " + currentValue
			// println ""

			// remove character from front, and PERMUTEIT
			PERMUTEIT(p.substring(1))
		}

		finalOffset = finalOffset + currentValue
		return currentValue;
	}





/*
Helper Methods
*/

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


	public BigDecimal GetNextItemPermutations(String p) {
		int sizeOfWord = p.size()
		BigInteger numerator = new BigInteger(0);
		BigInteger denominator = new BigInteger(1);
		numerator = BigFactorial(sizeOfWord-1)
		
		Map<Character, BigInteger> repeatMap = new HashMap<Character, BigInteger>();
		p.each {
			if(repeatMap.containsKey(it)) {
				repeatMap.put(it, repeatMap.get(it) + 1)  
			}
			else
				repeatMap.put(it, 1)
		}
		

		repeatMap.each { k, v ->
			repeatMap.put(k, BigFactorial(v))
		}
		
		repeatMap.each { key, value ->
			denominator = denominator*value
		}

		


		// println "numerator/denominator ::" + numerator / denominator
		// println "num: " + numerator
		// println "denominator: " + denominator
		return numerator/denominator

	}  // end GetPermutation


	public BigInteger GetTotalPermutations(String p) {
		int sizeOfWord = p.size()
		BigInteger numerator = new BigInteger(0);
		BigInteger denominator = new BigInteger(1);
		numerator = BigFactorial(sizeOfWord)
		
		Map<Character, BigInteger> repeatMap = new HashMap<Character, BigInteger>();
		p.each {
			if(repeatMap.containsKey(it)) {
				repeatMap.put(it, repeatMap.get(it) + 1)  
			}
			else
				repeatMap.put(it, 1)
		}		

		repeatMap.each { k, v ->
			repeatMap.put(k, BigFactorial(v))
		}
		
		repeatMap.each { key, value ->
			denominator = denominator*value
		}

		return numerator/denominator
	}
/*
End Helper Methods
*/



} // end class