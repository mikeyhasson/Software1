package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {
	
	public static final int UNRANKED_CONST = 30;
	public static final String FILE_INDEX_EXCEPETION_MSG = "a";
	HashMap <String, RankedWord> rankedWords;
	HashMap <String,HashMapHistogram<String>> histogramsMap;
	HashMap <String, Integer> histogramSizes;
	HashMap <rankType, Integer> defaultValues;
	
	public FileIndex() {
		histogramsMap=new HashMap <>();
		rankedWords = new HashMap <>();
		histogramSizes = new HashMap <>();
		defaultValues = new HashMap <>();
	}
	

	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 */
  	public void indexDirectory(String folderPath) {
		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		for (File file : listFiles) {
			// for every file in the folder
			if (file.isFile()) {
				try {
					initializeFile(file);//Initializing histogramsMap and histogramSizes.
				} catch (IOException e) {
				} 
				
			}
		}
		//initializing defaultValues.
		initializeDefaultValues();
		initializeRankedWords();
	}
	
  	private void initializeRankedWords() {
  		Map <String,Map<String, Integer>> rankMapsByWord= new HashMap<>();
  		for (String word: rankedWords.keySet()) {
			Map<String, Integer> ranksForFiles = new HashMap<>();
			rankMapsByWord.put(word, ranksForFiles);
  		}
  		for (String fileName: histogramsMap.keySet()) {
  			HashMapHistogram<String> fileHisto = histogramsMap.get(fileName);
  			ArrayList<String> lst = new ArrayList<>(fileHisto.getItemsSet());
  			lst.sort(fileHisto);
			for (String word:rankMapsByWord.keySet()) {
				int index = lst.indexOf(word);
				if (index == -1)
					index = defRank(fileName);
				else
					index+=1;
				rankMapsByWord.get(word).put(fileName,index);
			}
		}
  		for (String word: rankedWords.keySet()) {
  			RankedWord r = new RankedWord(word, rankMapsByWord.get(word));
  			rankedWords.replace(word, r);
  		}
		
	}


	private void initializeDefaultValues() {
		int sum=0;
		int min=0;
		int max=0;
		for (int i:histogramSizes.values()) {
			sum+=i;
			min = (min == 0)? i : Math.min(min, i);
			max = Math.max(max, i);
		}
		int numOfFiles = histogramSizes.size();
		sum = (int)Math.round(((double)sum)/numOfFiles) + UNRANKED_CONST;
		min += UNRANKED_CONST;
		max +=UNRANKED_CONST;
		defaultValues.put(rankType.min, min);
		defaultValues.put(rankType.max, max);
		defaultValues.put(rankType.average, sum);
	}


	private void initializeFile(File file) throws IOException {
		List<String> lst = FileUtils.readAllTokens(file);
		String filename = file.getName();
		HashMapHistogram <String> histo = new HashMapHistogram <>();
		histogramsMap.put(filename, histo);	
		for (String word:lst) {
			histo.addItem(word);
			rankedWords.put(word, null);
		}
		histogramSizes.put(filename, histo.size());
	}


	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getCountInFile(String filename, String word) throws FileIndexException{	
		return fileNameCheck(filename).getCountForItem(word.toLowerCase());
	}
	
	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getRankForWordInFile(String filename, String word) throws FileIndexException{
		HashMapHistogram<String> histo = fileNameCheck(filename);
		word=word.toLowerCase();
		RankedWord rankedWordItem = rankedWords.get(word);
		if (histo.getCountForItem(word)== 0){//word not in the file.
			return defRank(filename);
		}
		Integer rankInFile = rankedWordItem.getRanksForFile().get(filename);//getting rank
		return rankInFile;

	}
	
	private int defRank(String fileName) {
		return histogramSizes.get(fileName) + UNRANKED_CONST;
	}


	private HashMapHistogram <String> fileNameCheck(String filename) throws FileIndexException{	
		HashMapHistogram <String> fileHisto = histogramsMap.get(filename);
		if (fileHisto == null)//no histogram for the file - not in index.
			throw new FileIndexException(FILE_INDEX_EXCEPETION_MSG);
		return fileHisto;
	}
	/*
	 * @pre: the index is initialized
	 * @pre word is not null
	 */
	public int getAverageRankForWord(String word){
		return getTypeRankForWord(word,rankType.average);
	}
	
	public int getTypeRankForWord(String word, rankType type) {
		RankedWord rankedWordItem = rankedWords.get(word.toLowerCase());
		if (rankedWordItem != null)//word is in a file
			return rankedWordItem.getRankByType(type);
		return defaultValues.get(type);
		}
	
	public List<String> getWordsWithTypeRankSmallerThanK(int k, rankType type){
		ArrayList<RankedWord> lst = new ArrayList<>(rankedWords.values()); 
		lst.sort(new RankedWordComparator(type));
		RankedWord obj=null;
		for (RankedWord a: lst)
			if (a.getRankByType(type) >= k) {
				obj=a;
				break;
			}
		int lastIndex;
		if (obj == null) //all smaller than k.
			lastIndex=lst.size();
		else
			lastIndex =lst.indexOf(obj);
		
		List <String> wordsList = new ArrayList<>();
		if (lastIndex == 0)//all bigger or equal to k
			return wordsList;//empty list.
		for (int i=0;i<lastIndex;i++)
			wordsList.add(lst.get(i).getWord());
		
		return wordsList;
	}
	
	public List<String> getWordsWithAverageRankSmallerThanK(int k){
		
		return getWordsWithTypeRankSmallerThanK (k,rankType.average);
	}
	
	public List<String> getWordsWithMinRankSmallerThanK(int k){
		return getWordsWithTypeRankSmallerThanK (k,rankType.min);
	}
	
	public List<String> getWordsWithMaxRankSmallerThanK(int k){
		return getWordsWithTypeRankSmallerThanK (k,rankType.max);
	}

}
