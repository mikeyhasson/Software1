package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T>{
	private Map<T, Integer> map;
	private Iterator<T> iterator;
	
	public HashMapHistogramIterator (Map<T, Integer> orgMap,HashMapHistogram<T> histo) {
		map=new TreeMap<>(new HashMapHistogramComparator<T>(histo));
		map.putAll(orgMap);
		iterator = map.keySet().iterator();
		}
	
	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public T next() {
		return iterator.next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(); //no need to change this
	}
}
