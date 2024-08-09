	@Test
	public void getConceptsByAnswer_shouldReturnAnEmptyListIfConceptIdIsNull() {
		List<Concept> concepts = conceptService.getConceptsByAnswer(new Concept());
		assertEquals(concepts, Collections.emptyList());
	}