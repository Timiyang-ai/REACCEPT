@Test
	public void testGetCorpusRelationScores() {
		List<Concept> concepts = new ArrayList<Concept>();
		concepts.add(EXAMPLE_CONCEPT);
		Scores scores = service.getCorpusRelationScores(Corpus.IBM_RESEARCHERS, concepts);
		Assert.assertNotNull(scores);
	}