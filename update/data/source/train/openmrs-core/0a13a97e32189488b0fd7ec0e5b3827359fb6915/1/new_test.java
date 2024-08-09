@Test
	@Verifies(value = "return distinct drugs", method = "getDrugs(String,Concept,boolean,boolean,boolean,Integer,Integer)")
	public void getDrugs_shouldReturnDistinctDrugs() throws Exception {
		Concept concept1 = dao.getConcept(14);
		
		List<Drug> drugList = dao.getDrugs("TEST_DRUG", concept1, true, true, false, 0, 10);
		Assert.assertEquals(1, drugList.size());
		
	}