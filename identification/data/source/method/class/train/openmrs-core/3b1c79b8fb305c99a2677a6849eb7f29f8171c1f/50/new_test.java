	@Test
	public void getConceptReferenceTermByUuid_shouldReturnTheConceptReferenceTermThatMatchesTheGivenUuid() {
		Assert.assertEquals("weight term2", Context.getConceptService().getConceptReferenceTermByUuid("SNOMED CT-2332523")
		        .getName());
	}