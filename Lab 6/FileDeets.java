import java.io.*;
import java.util.*;
import java.nio.file.*;

public class FileDeets{

	public static void main(String[] args)
	{
		String filePath = "text.txt";
		String str = Readfile(filePath);
		Vowelcounts(str);
		ReadDigits(str);
		RepeatedWords(str);
		str = str.replaceAll("\\s+","");
		str = str.toLowerCase();
		String[] strArray = str.split("");
		String[] uniqueArray = getUniqueArray(strArray);
		int[] countArray = getCountArray(strArray, uniqueArray);
		sortArrays(uniqueArray, countArray);

		printMostRepeated(uniqueArray, countArray, 5);
		printLeastRepeated(uniqueArray, countArray, 5);
	}	


	public static String Readfile(String filePath)
	{
		//Reading the text file and converting it into a string

		Path path = Paths.get(filePath);
		String content = "";
		try{	
			content = Files.readString(path);
		}catch(IOException e){
			e.printStackTrace();
		}
		return content;
	}

	public static void Vowelcounts(String str){
		//Counting the vowels in the text file

		str = str.toLowerCase();

		int counta = 0;
		int counte = 0;
		int counti = 0;
		int counto = 0;
		int countu = 0;

		for(int i = 0; i < str.length(); i++){

			if(str.charAt(i) == 'a'){
				counta++;
			}
			if(str.charAt(i) == 'e'){
				counte++;
			}
			if(str.charAt(i) == 'i'){
				counti++;
			}
			if(str.charAt(i) == 'o'){
				counto++;
			}
			if(str.charAt(i) == 'u'){
				countu++;
			}	
		}
		System.out.println("\n"+"The vowels in the text and their counts are: " + "\n" + "\n" + "a: "+counta+"\n"+"e: "+counte+"\n"+"i: "+counti+"\n"+"o: "+counto+"\n"+"u: "+countu);
	}
	public static void ReadDigits(String str){

		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
		int count6 = 0;
		int count7 = 0;
		int count8 = 0;
		int count9 = 0;	


		for(int i = 0;i < str.length();i++){
			if(str.charAt(i) == '1'){
				count1++;
			}
			if(str.charAt(i) == '2'){
				count2++;
			}
			if(str.charAt(i) == '3'){
				count3++;
			}
			if(str.charAt(i) == '4'){
				count4++;
			}
			if(str.charAt(i) == '5'){
				count5++;
			}
			if(str.charAt(i) == '6'){
				count6++;
			}
			if(str.charAt(i) == '7'){
				count7++;
			}
			if(str.charAt(i) == '8'){
				count8++;
			}
			if(str.charAt(i) == '9'){
				count9++;
			}

		}
		System.out.println("\n"+"The digits in the text and their count are: "+"\n");
		System.out.println("One: " + count1);
		System.out.println("Two: " + count2);
		System.out.println("Three: " + count3);
		System.out.println("Four: " + count4);
		System.out.println("Five: " + count5);
		System.out.println("Six: " + count6);
		System.out.println("Seven: " + count7);
		System.out.println("Eight: " + count8);
		System.out.println("Nine: " + count9);
	}

	public static int findIndex(String[] array, String target, int count){
		for(int i = 0; i < count; i++){
			if(array[i].equals(target)){
				return i;
			}
		}
		return -1;
	}

	public static void RepeatedWords(String str){
		str = str.replace(".","");
		String[] words = str.split("[\\s\\-,\\%]+");
		String[] uniqueWords = new String[words.length];
		int[] frequencies = new int[words.length];
		int uniqueWordCount = 0;
		for(int i = 0; i < words.length; i++){
			int index = findIndex(uniqueWords, words[i], uniqueWordCount);
			if(index == -1){
				uniqueWords[uniqueWordCount] = words[i];
				frequencies[uniqueWordCount] = 1;
				uniqueWordCount++;
			}else{
				frequencies[index]++;
			}
		}
		int[] topFrequencies = new int[5];
		String[] topWords = new String[5];
		for(int i = 0; i < uniqueWordCount; i++){
			for(int j = 0; j < 5; j++){
				if(frequencies[i] > topFrequencies[j]){
					for(int k = 4; k > j; k--){
						topFrequencies[k] = topFrequencies[k-1];
						topWords[k] = topWords[k-1];
					}
					topFrequencies[j] = frequencies[i];
					topWords[j] = uniqueWords[i];
					break;
				}	
			}
		}
		System.out.println("\n" + "The top 5 repeated words are: " + "\n");
		for(int i = 0; i < 5; i++){
			if(topFrequencies[i] == 0){
				break;
			}
			System.out.println(topWords[i] + ": " + topFrequencies[i]);
		}
		int[] leastFrequencies = new int[5];
		String[] leastWords = new String[5];
		Arrays.fill(leastFrequencies, Integer.MAX_VALUE);
		for(int i = 0; i < uniqueWordCount; i++){
			if(frequencies[i] > 1){
				for(int j = 0; j < 5; j++){
					if(frequencies[i] < leastFrequencies[j]){
						for(int k = 4; k > j; k--){
							leastFrequencies[k] = leastFrequencies[k-1];
							leastWords[k] = leastWords[k-1];
						}
						leastFrequencies[j] = frequencies[i];
						leastWords[j] = uniqueWords[i];
						break;
					}
				}
			}
		}
		System.out.println("\n" + "The least five repeated words are: " + "\n");
		for(int i = 0; i < 5; i++){
			if(leastFrequencies[i] == Integer.MAX_VALUE){
				break;
			}
			System.out.println(leastWords[i] + ": " + leastFrequencies[i]);
		}
	}

	public static String[] getUniqueArray(String[] strArray) {
		return Arrays.stream(strArray)
			.filter(s -> Character.isLetterOrDigit(s.charAt(0)))
			.distinct()
			.toArray(String[]::new);
	}

	public static int[] getCountArray(String[] strArray, String[] uniqueArray) {
		int[] countArray = new int[uniqueArray.length];
		for (int i = 0; i < uniqueArray.length; i++) {
			for (String s : strArray) {
				if (s.equals(uniqueArray[i])) {
					countArray[i]++;
				}
			}
		}
		return countArray;
	}

	public static void sortArrays(String[] uniqueArray, int[] countArray) {
		for (int i = 0; i < countArray.length - 1; i++) {
			for (int j = i + 1; j < countArray.length; j++) {
				if (countArray[i] < countArray[j]) {
					int tempCount = countArray[i];
					countArray[i] = countArray[j];
					countArray[j] = tempCount;

					String tempChar = uniqueArray[i];
					uniqueArray[i] = uniqueArray[j];
					uniqueArray[j] = tempChar;
				}
			}
		}
	}

	public static void printMostRepeated(String[] uniqueArray, int[] countArray, int n) {
		System.out.println("\n"+"The " + n + " most repeated characters are:"+"\n");
		for (int i = 0; i < n; i++) {
			System.out.println(uniqueArray[i] + " - " + countArray[i]);
		}
	}

	public static void printLeastRepeated(String[] uniqueArray, int[] countArray, int n) {
		System.out.println("\n"+"The " + n + " least repeated characters are:"+"\n");
		for (int i = uniqueArray.length - 1; i >= uniqueArray.length - n; i--) {
			System.out.println(uniqueArray[i] + " - " + countArray[i]);
		}
	}
}

