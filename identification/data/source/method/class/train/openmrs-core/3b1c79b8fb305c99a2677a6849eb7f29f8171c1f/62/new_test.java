	@Test
	public void getConceptMapTypeByName_shouldReturnAConceptMapTypeMatchingTheSpecifiedName() {
		Assert.assertEquals("same-as", Context.getConceptService().getConceptMapTypeByName("same-as").getName());
	}