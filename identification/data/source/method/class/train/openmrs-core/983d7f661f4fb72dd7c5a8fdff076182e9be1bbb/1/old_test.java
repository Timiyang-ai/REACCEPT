	@Test
	public void purgeConceptMapType_shouldDeleteTheSpecifiedConceptMapTypeFromTheDatabase() {
		Integer conceptMapTypeId = 8;
		ConceptMapType mapType = conceptService.getConceptMapType(conceptMapTypeId);
		assertNotNull(mapType);
		conceptService.purgeConceptMapType(mapType);
		assertNull(conceptService.getConceptMapType(conceptMapTypeId));
	}