public static void main(String[] args) throws Exception {
		
		String BASE_DIR = "/home/dev/workspace/docx4j/src/test/java/org/docx4j/diff/";
		
		// Test setup
		String paraL = BASE_DIR + "t2R";		
		String paraR = BASE_DIR + "t3L";
		P pl = loadParagraph(paraL);
		P pr = loadParagraph(paraR);
		
		// Result format
		StreamResult result = new StreamResult(System.out);

		// Run the diff
		diff(pl, pr, result);
		
	}