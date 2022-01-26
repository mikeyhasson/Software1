package il.ac.tau.cs.sw1.ex8.histogram;
import java.util.Comparator;

public class HashMapHistogramComparator<T extends Comparable<T>> implements Comparator<T>{
	
	HashMapHistogram<T> histo;	
	public HashMapHistogramComparator(HashMapHistogram<T> histo) {
		this.histo=histo;
		}
	
	public int compare (T item1, T item2) {
		int countDiff =histo.getCountForItem(item1)-histo.getCountForItem(item2);
		if (countDiff < 0)
			return 1;
		if (countDiff > 0)
			return -1;
		return item1.compareTo(item2);
	}	
}