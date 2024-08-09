	@Test
	public void getConceptMapTypes_shouldNotIncludeHiddenConceptMapTypesIfIncludeHiddenIsSetToFalse() {
		Assert.assertEquals(6, Context.getConceptService().getConceptMapTypes(true, false).size());
	}