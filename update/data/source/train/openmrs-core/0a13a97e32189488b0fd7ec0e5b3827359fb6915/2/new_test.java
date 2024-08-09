@Test
	@Verifies(value = "return a drug if phrase match drug_name No need to match both concept_name and drug_name", method = "getDrugs(String,Concept,boolean,boolean,boolean,Integer,Integer)")
	public void getDrugs_shouldReturnDrugIfPhraseMatchDrugNameNoNeedToMatchBothConceptNameAndDrugName() throws Exception {
		// This concept does not contain concept_name with "Triomune"
		Concept concept2 = dao.getConcept(3);
		
		// In this test there is no any concept_name match with "Triomune" but
		// Drug name match with "Trimonue" so no need to match both drug_name
		// and the concept_name to find drug
		List<Drug> drugList = dao.getDrugs("Triomune", concept2, true, true, false, 0, 10);
		Assert.assertEquals(1, drugList.size());
		
	}