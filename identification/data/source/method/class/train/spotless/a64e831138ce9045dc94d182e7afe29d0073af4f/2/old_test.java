	private void fixWindowsBugTestcase(String... lines) {
		String input = StringPrinter.buildStringFromLines(lines);
		Assert.assertEquals(input, GoogleJavaFormatStep.fixWindowsBug(input, "1.1"));
	}