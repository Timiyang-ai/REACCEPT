	@Test
	public void getDrugs_shouldReturnListOfMatchingDrugs() {
		String drugName = "ASPIRIN";
		String drugUuid = "05ec820a-d297-44e3-be6e-698531d9dd3f";
		Concept concept = conceptService.getConceptByUuid(drugUuid);
		List<Drug> drugs = conceptService.getDrugs(drugName, concept, true, true, true, 0, 100);
		assertTrue(drugs.contains(conceptService.getDrug(drugName)));
	}