	@Test
	public void getConceptReferenceTerms_shouldReturnAllTheConceptReferenceTermsIfIncludeRetiredIsSetToTrue()
	{
		Assert.assertEquals(11, Context.getConceptService().getConceptReferenceTerms(true).size());
	}