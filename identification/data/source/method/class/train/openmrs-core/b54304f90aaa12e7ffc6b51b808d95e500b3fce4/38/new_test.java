	@Test
	public void addConceptReferenceTermMap_shouldSetTermAAsTheTermToWhichAMappingIsBeingAdded() {
		ConceptReferenceTerm term = new ConceptReferenceTerm(2);
		term.addConceptReferenceTermMap(new ConceptReferenceTermMap(new ConceptReferenceTerm(1), new ConceptMapType(1)));
		Assert.assertEquals(true, term.equals(term.getConceptReferenceTermMaps().iterator().next().getTermA()));
	}