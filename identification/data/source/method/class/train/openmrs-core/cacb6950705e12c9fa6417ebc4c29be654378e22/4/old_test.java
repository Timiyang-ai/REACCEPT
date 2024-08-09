	@Test
	public void getQueryParts_shouldProcessSimpleSpaceAsSeparator() {
		String[] actual = patientSearchCriteria.getQueryParts("Anton Bert Charles");
		String[] expected = { "Anton", "Bert", "Charles" };
		
		Assert.assertArrayEquals(expected, actual);
	}