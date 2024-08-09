	@Test
	public void getReferenceTermMappingsTo_shouldReturnAllConceptReferenceTermMapsWhereTheSpecifiedTermIsTheTermB()
	{
		Assert.assertEquals(2, Context.getConceptService().getReferenceTermMappingsTo(
		    Context.getConceptService().getConceptReferenceTerm(4)).size());
	}