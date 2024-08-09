public CsvData read(Reader reader) throws IORuntimeException {
		final CsvParser csvParser = parse(reader);

		final List<CsvRow> rows = new ArrayList<>();
		CsvRow csvRow;
		while ((csvRow = csvParser.nextRow()) != null) {
			rows.add(csvRow);
		}

		final List<String> header = config.containsHeader ? csvParser.getHeader() : null;
		return new CsvData(header, rows);
	}