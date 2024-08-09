	@Test
	public void getDefaultConceptMapType_shouldReturnSameAsByDefault() {
		ConceptMapType conceptMapType = conceptService.getDefaultConceptMapType();
		Assert.assertNotNull(conceptMapType);
		Assert.assertEquals("same-as", conceptMapType.getName());
	}