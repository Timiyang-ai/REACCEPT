public static void main(String[] args) throws Exception {

		// Test setup
		String paraL = "/home/dev/workspace/docx4j/sample-docs/diff/t3L";		
		String paraR = "/home/dev/workspace/docx4j/sample-docs/diff/t3R";
		P pl = loadParagraph(paraL);
		P pr = loadParagraph(paraR);
		
		// Result format
		StreamResult result = new StreamResult(System.out);

		// Run the diff
		diff(pl, pr, result);
		
	}