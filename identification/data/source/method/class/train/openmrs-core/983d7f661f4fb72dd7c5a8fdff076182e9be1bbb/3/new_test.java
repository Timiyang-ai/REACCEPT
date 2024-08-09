	@Test
	public void getAllConceptReferenceTerms_shouldReturnAllConceptReferenceTermsInTheDatabase() {
		Assert.assertEquals(11, Context.getConceptService().getAllConceptReferenceTerms().size());
	}