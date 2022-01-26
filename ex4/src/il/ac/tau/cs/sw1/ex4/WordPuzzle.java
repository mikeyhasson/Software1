package il.ac.tau.cs.sw1.ex4;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordPuzzle {
	public static final char HIDDEN_CHAR = '_';
	public static final int MAX_VOCABULARY_SIZE = 3000;
	public static final int TRIES_ADDITION = 3;
	public static final char GET_HELP_CHAR = 'H';


	public static boolean isWord(String curWord) {
		char[] array=curWord.toCharArray();
		if (array.length == 1)
			return false;
		for (char a:array) {
			if (!Character.isLetter(a))
				return false;
		}	
		return true;
	}

	public static boolean isDuplicate(String[] arr,String newWord) {
		for (int i=0;i<arr.length;i++) {
			if (arr[i] == null)
				return false;
			if (newWord.equals(arr[i]))
				return true;
			}
		return false;
	}

	public static String[] scanVocabulary(Scanner scanner) { // Q - 1
		String[] arr=new String[MAX_VOCABULARY_SIZE];
		int numOfWords=0;
		String curWord;
		while (scanner.hasNext() && numOfWords < MAX_VOCABULARY_SIZE) {
			curWord = scanner.next();
			curWord.toLowerCase();
			if (isWord(curWord)) {
				if (!isDuplicate(arr,curWord)) {
				arr[numOfWords]=curWord;
				numOfWords++;
				}
			}
		}
		String[] newArr= Arrays.copyOf(arr,numOfWords);
		Arrays.sort(newArr);
		return newArr;
		}
		

	public static int countHiddenInPuzzle(char[] puzzle) { // Q - 2
		int numOfHiddenChars=0;
		for (char a:puzzle) {
			if (a == HIDDEN_CHAR)
				numOfHiddenChars++;
		}
		return numOfHiddenChars;
	}

	public static String getRandomWord(String[] vocabulary, Random generator) { // Q - 3
		int i = generator.nextInt(vocabulary.length);
		return vocabulary[i];
	}

	public static boolean checkLegal(String word, char[] puzzle) { // Q - 4
		int numOfHidden = countHiddenInPuzzle(puzzle);
		if (numOfHidden == 0 || numOfHidden == puzzle.length)
			return false;
		for (int i=0;i<word.length()-1;i++) {
			char curLetter=word.charAt(i);//going through letters
			char curLetterInPuzzle=puzzle[i];//letter equivalent in puzzle
			int firstIndex= word.indexOf(curLetter);//first index of letter
			char firstIndexInPuzzle=puzzle[firstIndex];//first index of letter in puzzle
			if (firstIndexInPuzzle !=curLetterInPuzzle)
				return false;
		}
		return true;
	}

	public static char[] getRandomPuzzleCandidate(String word, double prob, Random generator) { // Q - 5
		char[] puzzle= word.toCharArray();
		for (int i=0;i<puzzle.length;i++) {
			if (generator.nextFloat() <= prob)
				puzzle[i]=HIDDEN_CHAR;
		}
		return puzzle;
	}

	public static char[] getRandomPuzzle(String word, double prob, Random generator) { // Q - 6
		for (int i=0;i<1000;i++) {
			char[] curPuzzle=getRandomPuzzleCandidate(word,prob,generator);
			if (checkLegal(word,curPuzzle))
				return curPuzzle;
		}
		throwPuzzleGenerationException();
		return null;
	}

	public static int applyGuess(char guess, String solution, char[] puzzle) { // Q - 7
		int count=0;
		int i=solution.indexOf(guess);
		while (i != -1) {
			if (puzzle[i] != HIDDEN_CHAR)
				return count;
			count++;
			puzzle[i]=guess;
			i=solution.indexOf(guess,i+1);
		}
		return count;
	}

	public static char[] getHelp(String solution, char[] puzzle) { // Q - 8
		int i=0;
		while (i<puzzle.length) {
			if (puzzle[i] == HIDDEN_CHAR)
				break;
			i++;
		}
		char helpChar=solution.charAt(i);
		while (i != -1) {
			puzzle[i]=helpChar;
			i=solution.indexOf(helpChar,i+1);
		}
		return puzzle;
	}
	
	public static void gameStage(char[] puzzle, String solution, Scanner s) {
		printGameStageMessage();
		int tries=countHiddenInPuzzle(puzzle) + TRIES_ADDITION;
		while (true) {
			printPuzzle(puzzle);
			printEnterYourGuessMessage();
			char guess= s.next().charAt(0);
			int numOfDiscovered=applyGuess(guess,solution,puzzle);
			if (countHiddenInPuzzle(puzzle) == 0) { //if there are no Hidden Chars, won game.
				printWinMessage();
				return;
				}
			tries--; //if didn't win yet, lower tries by one.
			if (numOfDiscovered > 0)
				printCorrectGuess(tries);	
			else if (guess != GET_HELP_CHAR)
				printWrongGuess(tries);
			else { //help
				getHelp(solution,puzzle);
				if (countHiddenInPuzzle(puzzle) == 0) {
					printWinMessage();
					return;
				}
			}
			if (tries == 0) {
				printGameOver();
				return;	
				}
		}
		
	}

	public static char[] choosePuzzle(String solution, float hidingProbability, Random generator, Scanner s) {
		String answer;
		char[] puzzle;
		do {
			puzzle =getRandomPuzzle(solution,hidingProbability,generator);
			printPuzzle(puzzle);
			printReplacePuzzleMessage();
			answer=s.next();
			while (!answer.equals("no") && !answer.equals("yes")) {
				printReplacePuzzleMessage();
				answer=s.next();
			}
		} while (!answer.equals("no"));
		return puzzle;
	}

	
	public static void main(String[] args) throws Exception { // Q - 9
		if (args.length == 0) {//if filename was not sent in arguments, error.
			argumentListError();
			return;
		}
		String pathname = args[0];
		File f = new File(pathname);
		Scanner s = new Scanner(f);
		String[] vocabulary = scanVocabulary(s);
		int vocabularySize = vocabulary.length;
		printReadVocabulary(pathname,vocabularySize);
		s = new Scanner(System.in);
		printSettingsMessage();
		printEnterHidingProbability();
		float hidingProbability= Float.parseFloat(s.next());
		Random generator = new MyRandom(getRrandomIntArr(vocabularySize), getRandomFloatArr());
		String solution = getRandomWord(vocabulary,generator);
		char[] puzzle=choosePuzzle(solution,hidingProbability,generator,s);
		gameStage(puzzle,solution,s);
		s.close();
	}
		
	/*************************************************************/
	/********************* Don't change this ********************/
	/*************************************************************/
	private static float[] getRandomFloatArr() {
		Double[] doubleArr = new Double[] { 0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0 };
		List<Double> doubleList = Arrays.asList(doubleArr);
		Collections.shuffle(doubleList);
		double[] unboxed = doubleList.stream().mapToDouble(Double::doubleValue).toArray();

		// cast double array to float array
		float[] floatArr = new float[unboxed.length];
		for (int i = 0; i < unboxed.length; i++) {
			floatArr[i] = (float) unboxed[i];
		}
		return floatArr;
	}

	private static int[] getRrandomIntArr(int vocabularySize) {
		
		if(vocabularySize<0) {
			throw new RuntimeException("Wrong use of getRandomIntArr(int vocabularySize)");
		}
		
		int i = 0;
		Integer[] intArr = new Integer[vocabularySize];
		while (i < vocabularySize) {
			intArr[i] = i;
			i++;
		}
		List<Integer> doubleList = Arrays.asList(intArr);
		Collections.shuffle(doubleList);
		int[] unboxed = doubleList.stream().mapToInt(Integer::intValue).toArray();
		return unboxed;
	}

	public static void throwPuzzleGenerationException() {
		throw new RuntimeException("Failed creating a legal puzzle after 1000 attempts!");
	}
	
	public static void argumentListError() {
		System.out.println("Argument list does not contain file name");
	}
	public static void printReadVocabulary(String vocabularyFileName, int numOfWords) {
		System.out.println("Read " + numOfWords + " words from " + vocabularyFileName);
	}

	public static void printSettingsMessage() {
		System.out.println("--- Settings stage ---");
	}

	public static void printEnterHidingProbability() {
		System.out.println("Enter your hiding probability:");
	}

	public static void printPuzzle(char[] puzzle) {
		System.out.println(puzzle);
	}

	public static void printReplacePuzzleMessage() {
		System.out.println("Replace puzzle?");
	}

	public static void printGameStageMessage() {
		System.out.println("--- Game stage ---");
	}

	public static void printEnterYourGuessMessage() {
		System.out.println("Enter your guess:");
	}

	public static void printCorrectGuess(int attemptsNum) {
		System.out.println("Correct Guess, " + attemptsNum + " guesses left");
	}

	public static void printWrongGuess(int attemptsNum) {
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left");
	}

	public static void printWinMessage() {
		System.out.println("Congratulations! You solved the puzzle");
	}
	public static void printGameOver() {
		System.out.println("Game over!");
	}

}
