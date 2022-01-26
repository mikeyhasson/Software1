package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.Iterator;

public class B4 implements Iterator<String> {
	
	int i;
	String[] lst;
	public B4(String[] strings, int k) {
		this.lst=new String[1+ strings.length  * k];
		createList(strings,k);
	}

	private void createList(String[] strings,int k) {
		for (int i = 0; i < strings.length; i++)
			for (int j = 0; j < k; j++)
				lst[i+strings.length*j]=strings[i];
	}
			
	@Override
	public boolean hasNext() {
		return i<lst.length-1;
	}

	@Override
	public String next() {
		i++;
		return lst[i-1];
	}
	
}
