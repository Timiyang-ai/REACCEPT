@Test
	public void testSetText() {
		HSLFSlide slideOne = ss.getSlides().get(0);
		List<List<HSLFTextParagraph>> textRuns = slideOne.getTextParagraphs();
		HSLFTextParagraph run = textRuns.get(0).get(0);
		HSLFTextRun tr =  run.getTextRuns().get(0);

		// Check current text
		assertEquals("This is a test title", tr.getRawText());

		// Change
		String changeTo = "New test title";
		tr.setText(changeTo);
		assertEquals(changeTo, tr.getRawText());

		// Ensure trailing \n's are NOT stripped, it is legal to set a text with a trailing '\r'
		tr.setText(changeTo + "\n");
		assertEquals(changeTo + "\n", tr.getRawText());
	}