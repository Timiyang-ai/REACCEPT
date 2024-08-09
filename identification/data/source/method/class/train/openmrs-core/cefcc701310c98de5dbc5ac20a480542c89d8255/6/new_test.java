	@Test
	public void setAsText_shouldSetTheSortWeightsWithTheLeastPossibleChanges() {
		ConceptService service = Context.getConceptService();
		Concept c = service.getConcept(21);
		
		ConceptAnswersEditor editor = new ConceptAnswersEditor(c.getAnswers(true));
		editor.setAsText("22 7 8");
		
		ConceptAnswer ca1 = service.getConceptAnswer(1);//conceptId=7
		ConceptAnswer ca2 = service.getConceptAnswer(2);//conceptId=8
		ConceptAnswer ca3 = service.getConceptAnswer(3);//conceptId=22
		
		Concept cafter = service.getConcept(21);
		Assert.assertEquals(3, cafter.getAnswers(true).size());
		Assert.assertTrue(ca3.getSortWeight() < ca1.getSortWeight());
		Assert.assertTrue(ca1.getSortWeight() < ca2.getSortWeight());
	}