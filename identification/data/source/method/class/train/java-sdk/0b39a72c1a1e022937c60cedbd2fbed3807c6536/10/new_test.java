@Test
	public void testGetConcept() {
		ConceptMetadata conceptMetaData = service.getConcept(EXAMPLE_CONCEPT);
		Assert.assertNotNull(conceptMetaData);

	}