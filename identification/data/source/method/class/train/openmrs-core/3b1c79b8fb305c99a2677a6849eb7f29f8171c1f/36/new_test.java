	@Test
	public void getActiveConceptMapTypes_shouldReturnAllTheConceptMapTypesExcludingHiddenOnes() {
		Assert.assertEquals(6, Context.getConceptService().getActiveConceptMapTypes().size());
	}