	@Test
	public void getConceptsByMapping_shouldGetConceptsWithGivenCodeAndSourceH17Code() {
		List<Concept> concepts = conceptService.getConceptsByMapping("127689", "Some Standardized Terminology");
		Assert.assertEquals(2, concepts.size());
		Assert.assertTrue(containsId(concepts, 16));
		Assert.assertTrue(containsId(concepts, 6));
	}