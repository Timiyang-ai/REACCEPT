	@Test
	public void toCsvString() {
		assertThat(CsvUtil.toCsvString(1, 2)).isEqualTo("1,2");

		assertThat(CsvUtil.toCsvString(1, 2, 3, 4)).isEqualTo("1,2,3,4");

		// "2" still plain as 2
		assertThat(CsvUtil.toCsvString(1, "2")).isEqualTo("1,2");

		// "A BC" still plain as A BC
		assertThat(CsvUtil.toCsvString(1, "A BC")).isEqualTo("1,A BC");

		// "A,BC" has ',' as "A,BC"
		assertThat(CsvUtil.toCsvString(1, "A,BC")).isEqualTo("1,\"A,BC\"");

		// "A"BC" has '"' as "A""BC"
		assertThat(CsvUtil.toCsvString(1, "A\"BC")).isEqualTo("1,\"A\"\"BC\"");

		// "A,B"a"C" has 2 '""' as "A,""a""BC"
		assertThat(CsvUtil.toCsvString(1, "A,\"a\"BC")).isEqualTo("1,\"A,\"\"a\"\"BC\"");
	}