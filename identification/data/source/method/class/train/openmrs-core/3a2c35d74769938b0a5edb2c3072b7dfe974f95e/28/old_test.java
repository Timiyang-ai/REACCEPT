	@Test
	public void getConcept_shouldReturnNullGivenNullParameter() {
		Assert.assertNull(Context.getConceptService().getConcept((String) null));
	}