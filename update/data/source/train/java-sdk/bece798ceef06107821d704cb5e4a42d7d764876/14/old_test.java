@Test
	public void testGetDocumentRelationScores() {
		List<Concept> concepts = new ArrayList<Concept>();
		concepts.add(EXAMPLE_CONCEPT);
		Scores scores = service.getDocumentRelationScores(EXAMPLE_DOCUMENT, concepts);
		Assert.assertNotNull(scores);
	}