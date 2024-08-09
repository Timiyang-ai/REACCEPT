	@Test
	public void addAnswer_shouldNotFailIfAnswersListIsNull() {
		ConceptAnswer ca = new ConceptAnswer(123);
		Concept c = new Concept();
		c.setAnswers(null); // make sure the list is null
		c.addAnswer(ca);
	}