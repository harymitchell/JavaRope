package rope;

import junit.framework.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestLarge extends TestCase {

	public static void main(String[] args) throws IOException {
		
//		This class tests three different large text files.  
//		I convert the files to my Rope class, as well as to string to compare.
//		Stats are displayed in nanoseconds, then there are a few tests.  
//		I have commented out testing on Oswald.txt, because it makes the execution time approach 20 minutes.
		
		// Files
		File file_ammendment = new File("src/rope/the_missing_13th.txt");
		File file_universe =   new File("src/rope/end_of_universe.txt");
		File file_oswald =   new File("src/rope/oswald.txt");

		// Ropes
		Rope rope_file_ammendment = new Rope(file_ammendment, 45);
		Rope rope_file_ammendment_test = new Rope(file_ammendment, 45);
		Rope rope_file_universe = new Rope(file_universe, 45);
		Rope rope_file_oswald = new Rope(file_oswald, 60);  // Heap space issues.
		Rope append_string_rope = new Rope("***Append Text***", 2);
		Rope insert_string_rope = new Rope("***Insert Text***", 2);

		// Strings
		String universe = null;
//		String universe2=null;
		String ammendment = null;
		String insert_string_text =("***Insert Text***");
		String append_string_text =("***Append Text***");
		String oswald = null;	
		
		BufferedReader br = new BufferedReader(new FileReader(file_ammendment));
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			ammendment = sb.toString();			
		}
		catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();}
		}
		
		BufferedReader br2 = new BufferedReader(new FileReader(file_universe));
		StringBuilder sb2 = new StringBuilder();
		String line2;
		try {
			line2 = br2.readLine();
			while (line2 != null) {
				sb2.append(line2);
				sb2.append("\n");
				line2 = br2.readLine();
			}
			universe = sb2.toString();			
		}
		catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();}
		}
		
		BufferedReader br3 = new BufferedReader(new FileReader(file_oswald));
		StringBuilder sb3 = new StringBuilder();
		String line3;
		try {
			line3 = br3.readLine();
			while (line3 != null) {
				sb3.append(line3);
				sb3.append("\n");
				line3 = br3.readLine();
			}
			oswald = sb3.toString();			
		}
		catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();}
		}
		
		////////// TESTING EFFICIENCY ////////////
		
		System.out.println("Testing efficiency..."); 
		System.out.println();		System.out.println("\t\t\t\t**String and StringBuffer Tests");		System.out.println();
		String ammendment2 = null;
		
		ammendment2 = ammendment;
		long startTime = System.nanoTime();
		ammendment2.concat("Append");
		long endTime = System.nanoTime();
		System.out.println("Append String to Document String :                 " + (endTime-startTime) + " nanoseconds"); 
		
		startTime = System.nanoTime();
		ammendment2.concat(ammendment2);
		endTime = System.nanoTime();
		System.out.println("Append Document String to another:                 " + (endTime-startTime) + " nanoseconds"); 
		
		StringBuffer sbuf = new StringBuffer(ammendment2);
		startTime = System.nanoTime();
		sbuf.insert(1000, insert_string_text);
		endTime = System.nanoTime();
		System.out.println("Insert Document String into Document StringBuffer:" + (endTime-startTime) + " nanoseconds"); 

		StringBuffer sbuf2 = new StringBuffer(universe);
		startTime = System.nanoTime();
		sbuf2.append(ammendment);
		endTime = System.nanoTime();
		System.out.println("Append Document String to Document StringBuffer:  " + (endTime-startTime) + " nanoseconds"); 
		
										System.out.println("\t\t\t\t**Rope Tests**");	
		
		long startTime4 = System.nanoTime();
		// TEST
		rope_file_ammendment.insertOnRope(insert_string_rope, 500 );
		long endTime4 = System.nanoTime();
		System.out.println("Insert one word Rope into Rope Document           " + (endTime4-startTime4) + " nanoseconds"); 
										
		startTime4 = System.nanoTime();
		// TEST
		rope_file_ammendment.insertOnRope(rope_file_ammendment,500 );
		endTime4 = System.nanoTime();   
		System.out.println("Insert Rope Document into Rope Document          " + (endTime4-startTime4) + " nanoseconds"); 
										
		startTime = System.nanoTime();
		rope_file_ammendment.rope_append("Append");
		endTime = System.nanoTime();
		System.out.println("Append one String to Rope:    			    " + (endTime-startTime) + " nanoseconds");
				
		long startTime2 = System.nanoTime();
		// TEST
		rope_file_ammendment.rope_append(append_string_rope);
		long endTime2 = System.nanoTime();
		System.out.println("Append one word Rope to Rope:			     " + (endTime2-startTime2) + " nanoseconds"); 
		
		long startTime5 = System.nanoTime();
		// TEST
		rope_file_ammendment.rope_append(append_string_text);
		long endTime5 = System.nanoTime();
		System.out.println("Append one word String to Rope:			     " + (endTime5-startTime5) + " nanoseconds"); 
				
		long startTime3 = System.nanoTime();
		// TEST
		rope_file_ammendment.rope_append(rope_file_oswald);
		long endTime3 = System.nanoTime();
		System.out.println("Append one Rope to another:  			     " + (endTime3-startTime3) + " nanoseconds"); 
		
		System.out.println("Checking assertions..."); System.out.println();
		
		// Test validity
		
		String universe_rope_string = rope_file_universe.RopeToString();
		String ammendment_rope_string = rope_file_ammendment_test.RopeToString();
//		String oswald_rope_string = rope_file_oswald.RopeToString();

		// strings have same length as Rope Strings?
//		assertEquals(universe.length(), rope_file_universe.RopeToString().length()); 
		assertEquals(ammendment.length(), rope_file_ammendment_test.RopeToString().length()); 
//		assertEquals(oswald.length(), rope_file_oswald.RopeToString().length()); 							// This test can be lengthy

		// strings have same content?
//		assertEquals(universe, universe_rope_string);
		assertEquals(ammendment, ammendment_rope_string); 
//		assertEquals(oswald, oswald_rope_string); 															// This test can be lengthy
	
		System.out.println("Assertions pass!");
		
	}

}
