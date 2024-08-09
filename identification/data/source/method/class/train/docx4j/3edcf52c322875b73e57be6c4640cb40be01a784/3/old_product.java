public static void main(String[] args) throws Exception {
		
		
		/*String[] runtContents = "|".split("\\" + RUN_DELIMITER);
		System.out.println( "'|' " + runtContents.length );
		
		runtContents = " |".split("\\" + RUN_DELIMITER);
		System.out.println( "' |' " + runtContents.length );

		runtContents = "| ".split("\\" + RUN_DELIMITER);
		System.out.println( "'| ' " + runtContents.length );
		
		runtContents = " | ".split("\\" + RUN_DELIMITER);
		System.out.println( "' | ' " + runtContents.length );*/
		

		// Test setup
		String paraL = ParagraphDifferencerTest.BASE_DIR + "t2RR";		
		String paraR = ParagraphDifferencerTest.BASE_DIR + "t3L";
		P pl = loadParagraph(paraL);
		P pr = loadParagraph(paraR);
		
		// Result format
		StreamResult result = new StreamResult(System.out);

		// Run the diff
		diff(pl, pr, result);
		
	}