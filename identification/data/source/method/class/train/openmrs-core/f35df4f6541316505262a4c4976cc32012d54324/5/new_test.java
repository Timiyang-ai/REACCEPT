	@Test
	public void getCountOfConcepts_shouldReturnACountOfUniqueConcepts() {
		executeDataSet("org/openmrs/api/include/ConceptServiceTest-names.xml");
		Assert.assertEquals(2, conceptService.getCountOfConcepts("trust", Collections.singletonList(Locale.ENGLISH), false,
		    null, null, null, null, null).intValue());
	}