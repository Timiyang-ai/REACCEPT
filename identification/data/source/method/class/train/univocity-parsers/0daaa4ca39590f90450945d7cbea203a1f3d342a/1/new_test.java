	private CsvWriterSettings getWriterSettings() {
		CsvWriterSettings out = new CsvWriterSettings();
		out.getFormat().setLineSeparator("\n");
		return out;
	}