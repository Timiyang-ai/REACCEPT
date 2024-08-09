	private CsvParserSettings getParserSettings() {
		CsvParserSettings out = new CsvParserSettings();
		out.getFormat().setLineSeparator("\n");
		return out;
	}