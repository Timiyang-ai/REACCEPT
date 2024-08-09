	@Test
	public void getCountOfEncounters_shouldGetTheCorrectCountOfUniqueEncounters() {
		executeDataSet(UNIQUE_ENC_WITH_PAGING_XML);
		Assert.assertEquals(4, Context.getEncounterService().getCountOfEncounters("qwerty", true).intValue());
	}