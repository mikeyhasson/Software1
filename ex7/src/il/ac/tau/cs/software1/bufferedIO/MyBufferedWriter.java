package il.ac.tau.cs.software1.bufferedIO;

import java.io.FileWriter;
import java.io.IOException;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class MyBufferedWriter implements IBufferedWriter{
	private FileWriter fWriter;
	private int bufferSize;
	private char[] buffer;
	private int lastIndex;

	public MyBufferedWriter(FileWriter fWriter, int bufferSize){
		this.fWriter=fWriter;
		this.bufferSize=bufferSize;
		this.buffer = new char[bufferSize];
	}

	
	@Override
	public void write(String str) throws IOException {
		for (char i:str.toCharArray()) {
			if (lastIndex == bufferSize) {
				fWriter.write(buffer);
				lastIndex=0;
			}
			buffer[lastIndex]=i;
			lastIndex++;
		}	

	}
	
	@Override
	public void close() throws IOException {
		fWriter.write(buffer,0,lastIndex);
		fWriter.close();
	}

}