package rope;

import java.io.IOException;

public class TestSmall {
	
	public final static int spacing = 4;
	
	public static void main (String[] args) throws IOException{
		
		// This class was an initial testing ground to check functionality. 
		// I have small left examples of inserts and appends. To see visually that everything works
		
		Rope test_rope2;
		String text = "The quick brown fox jumped over the lazy dog. The farmer turned a little butter into corn.";
		System.out.println("TEST Text:\n"+text);
		test_rope2 = new Rope(text, 4);		
		String test_rope2_string = test_rope2.RopeToString();
		
		System.out.println();
		System.out.println("Text length: "+text.length());
		System.out.println("Rope length: "+test_rope2_string.length());
	
		System.out.println();
		System.out.println("TEST beginning_of_rope():");
		System.out.println(test_rope2.root.beginning_of_rope().data);
		
		System.out.println();
		System.out.println("TEST end_of_rope():");
		System.out.println(test_rope2.end_of_rope().data);
		
		System.out.println();
		System.out.println("TEST RopeToString():");
		System.out.println(test_rope2_string);
		
		System.out.println();
		System.out.println("TEST printRope():");
		test_rope2.printRope();
		
		System.out.println();
		System.out.println("TEST explainRope:");
		test_rope2.explainRope();
		
		System.out.println();
		System.out.println("Append: 'Arose the dog and fell off the log.'");
		test_rope2.rope_append(" Arose the dog, and fell off the log.");
		
		System.out.println();
		System.out.println("TEST printRope():");
		test_rope2.printRope();
		
		System.out.println();
		System.out.println("\nTEST beginning_of_rope():");
		System.out.println(test_rope2.root.beginning_of_rope().data);
		
		System.out.println();
		System.out.println("TEST end_of_rope():");
		System.out.println(test_rope2.end_of_rope().data);
		
		System.out.println();
		System.out.println("Prepend: 'Our Story: '");
		test_rope2.rope_prepend("Our Story: ");
		
		System.out.println();
		System.out.println("TEST printRope():");
		test_rope2.printRope();
		
		System.out.println();
		System.out.println("\nTEST beginning_of_rope():");
		System.out.println(test_rope2.root.beginning_of_rope().data);
		
		System.out.println();
		System.out.println("TEST end_of_rope():");
		System.out.println(test_rope2.end_of_rope().data);

		System.out.println();	
		System.out.println("TEST deleteLeftNode()");	
		RopeNode node2 = new RopeNode();
		node2.left = new RopeNode("1234");
		node2.right = new RopeNode("5678");
		System.out.println("New simple Rope: ");	node2.printNode();		System.out.println();	
		node2.deleteLeftNode();
		System.out.println("delete left node: ");	node2.printNode();		System.out.println();	
		
		System.out.println();	
		System.out.println("TEST insert()");	

		StringBuffer textbuf = new StringBuffer("Our Story: The quick brown fox jumped over the lazy dog. The farmer turned a little butter into corn. Arose the dog, and fell off the log.");

		test_rope2.insertOnRope("**INSERTED PHRASE OR WORD**", 35);
		test_rope2.printRope(); System.out.println();	
		
		textbuf.insert(35, "**INSERTED PHRASE OR WORD**");
		System.out.println(textbuf.toString());
		
		test_rope2.insertOnRope("**INSERTED PHRASE OR WORD**", 45);
		test_rope2.printRope(); System.out.println();	
		
		textbuf.insert(45, "**INSERTED PHRASE OR WORD**");
		System.out.println(textbuf.toString());



	

	}


}
