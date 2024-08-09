	@Test
	public void fromCsvString() {
		assertThat(CsvUtil.fromCsvString("1,2")).hasSize(2).contains("1").contains("2");
		assertThat(CsvUtil.fromCsvString("1,A BC")).hasSize(2).contains("1").contains("A BC");
		assertThat(CsvUtil.fromCsvString("1,\"A,BC\"")).hasSize(2).contains("1").contains("A,BC");
		assertThat(CsvUtil.fromCsvString("1,\"A,\"\"a\"\"BC\"")).hasSize(2).contains("1").contains("A,\"a\"BC");

		// wrong format still work
		assertThat(CsvUtil.fromCsvString("1,\"A,\"a\"\"BC\"")).hasSize(2).contains("1").contains("A,\"a\"BC");
		assertThat(CsvUtil.fromCsvString("1,ABC\"")).hasSize(2).contains("1").contains("ABC\"");
	}