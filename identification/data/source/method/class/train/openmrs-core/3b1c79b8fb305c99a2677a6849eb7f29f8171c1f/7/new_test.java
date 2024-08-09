	@Test
	public void getCountOfConceptReferenceTerms_shouldIncludeRetiredTermsIfIncludeRetiredIsSetToTrue() {
		Assert.assertEquals(11, conceptService.getCountOfConceptReferenceTerms("", null, true).intValue());
	}