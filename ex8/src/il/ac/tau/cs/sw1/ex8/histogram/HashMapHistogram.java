package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>, Comparator<T>{
	private HashMap<T, Integer> map;
	
	public HashMapHistogram (){
		map = new HashMap<T, Integer>();
	}
	
	public Iterator<T> iterator() {
		return new HashMapHistogramIterator<T>(map, this);
	}

	@Override
	public void addItem(T item) {
		try {
			addItemKTimes(item,1);
		} catch (IllegalKValueException e) {//wont be thrown since k=1
		}
	}

	@Override
	public void removeItem(T item) throws IllegalItemException {
		try {
			removeItemKTimes (item,1);
		} catch (IllegalKValueException e) {//wont be thrown since k=1
		}
	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValueException {
		if (k < 1)
			throw new IllegalKValueException(k);
		Integer count = map.putIfAbsent(item,k);
		if (count != null)
			map.replace(item, count+k);
	}

	@Override
	public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException {
		if (k<1)
			throw new IllegalKValueException(k);
		int count = getCountForItem(item);
		if (count ==0)
			throw new IllegalItemException();
		if (count <= k)
			map.remove(item);
		else
			map.replace(item, count-k);
		
	}

	@Override
	public int getCountForItem(T item) {
		Integer count = map.get(item);
		if (count == null)
			return 0;
		return count;
	}

	@Override
	public void addAll(Collection<T> items) {
		for (T item:items)
			addItem(item);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Set<T> getItemsSet() {
		return map.keySet();
	}

	@Override
	public void update(IHistogram<T> anotherHistogram) {
		for (T item:anotherHistogram.getItemsSet())
			try {
				addItemKTimes(item,anotherHistogram.getCountForItem(item));
			} catch (IllegalKValueException e) {//nothing to catch.
			}
	}
	
	public int size() {
		return map.size();
	}
	
	public int compare(T o1, T o2) {
		HashMapHistogramComparator<T> comp = new HashMapHistogramComparator<>(this);
		return comp.compare(o1, o2);
	}
	
}
