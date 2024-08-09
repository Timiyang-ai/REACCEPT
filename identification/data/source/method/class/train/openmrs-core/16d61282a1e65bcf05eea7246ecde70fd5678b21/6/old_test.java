	@Test
	public void getConceptByName_shouldGetConceptByName() {
		
		String nameToFetch = "Some non numeric concept name";
		
		executeDataSet(INITIAL_CONCEPTS_XML);
		
		Concept conceptByName = conceptService.getConceptByName(nameToFetch);
		assertEquals("Unable to fetch concept by name", 1, conceptByName.getId().intValue());
	}