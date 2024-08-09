	@Test
	public void getConceptMappingsToSource_shouldReturnAListOfConceptMapsFromTheGivenSource() {
		Assert.assertEquals(8, Context.getConceptService().getConceptMappingsToSource(
		    Context.getConceptService().getConceptSource(1)).size());
	}