	@Test
	public void purgeConceptReferenceTerm_shouldPurgeTheGivenConceptReferenceTerm() {
		Integer conceptReferenceTermId = 11;
		ConceptReferenceTerm refTerm = conceptService.getConceptReferenceTerm(conceptReferenceTermId);
		conceptService.purgeConceptReferenceTerm(refTerm);
		assertNull(conceptService.getConceptReferenceTerm(conceptReferenceTermId));
	}