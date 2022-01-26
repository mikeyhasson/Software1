package il.ac.tau.cs.software1.bufferedIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedIOTester {
	public static final String RESOURCES_FOLDER = "resources/hw7/out/";

	public static void main(String[] args) throws IOException{
		String outputFileName = RESOURCES_FOLDER + "rocky1_out.txt";
		String outString1 = "No"; 
		FileWriter fWriter = new FileWriter(new File(outputFileName));
		IBufferedWriter bW = new MyBufferedWriter(fWriter, 5);
		bW.write(outString1);
		bW.close();
		
		/***
		 * The output file this tester creates should be identical to rocky1_correct.txt
		 * 
		 */
	}
}
