	@Test
	public void getConceptReferenceTermByName_shouldReturnAConceptReferenceTermThatMatchesTheGivenNameFromTheGivenSource()
	{
		ConceptReferenceTerm term = Context.getConceptService().getConceptReferenceTermByName("weight term",
		    new ConceptSource(1));
		Assert.assertNotNull(term);
		Assert.assertEquals("weight term", term.getName());
	}