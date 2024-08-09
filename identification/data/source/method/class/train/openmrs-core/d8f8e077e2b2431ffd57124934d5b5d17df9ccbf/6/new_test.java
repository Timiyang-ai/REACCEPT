	@Test
	public void getAllConceptNameTags_shouldReturnAListOfAllConceptNameTags() {
		int conceptNameTagsInDataset = 15;
		List<ConceptNameTag> conceptNameTags = conceptService.getAllConceptNameTags();
		assertEquals(conceptNameTagsInDataset, conceptNameTags.size());
	}