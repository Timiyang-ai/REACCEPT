	@Test
	public void mapConceptProposalToConcept_shouldThrowAPIExceptionWhenMappingToNullConcept() {
		ConceptProposal cp = conceptService.getConceptProposal(2);
		Locale locale = new Locale("en", "GB");
		expectedException.expect(APIException.class);
		conceptService.mapConceptProposalToConcept(cp, null, locale);
	}