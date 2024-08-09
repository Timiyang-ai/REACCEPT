	private void misbehaved(FormatterFunc step, String input, PaddedCell.Type expectedOutputType, String steps, String canonical) throws IOException {
		testCase(step, input, expectedOutputType, steps, canonical, true);
	}