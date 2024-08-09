	@Test
	public void getConceptReferenceTermByCode_shouldReturnAConceptReferenceTermThatMatchesTheGivenCodeFromTheGivenSource()
	{
		ConceptReferenceTerm term = Context.getConceptService().getConceptReferenceTermByCode("2332523",
		    new ConceptSource(2));
		Assert.assertNotNull(term);
		Assert.assertEquals("2332523", term.getCode());
	}