	@Test
	public void getAllConcepts_shouldExcludeRetiredConceptsWhenSetIncludeRetiredToFalse() {
		final List<Concept> allConcepts = conceptService.getAllConcepts(null, true, false);
		
		assertEquals(34, allConcepts.size());
		assertEquals(3, allConcepts.get(0).getConceptId().intValue());
	}