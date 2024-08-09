	@Test
	public void getDrugs_shouldReturnDistinctDrugs() {
		Concept concept1 = dao.getConcept(14);
		
		List<Drug> drugList = dao.getDrugs("TEST_DRUG", concept1, true, true, false, 0, 10);
		Assert.assertEquals(1, drugList.size());
		
	}