	@Test
	public void getAllCohorts_shouldGetAllNonvoidedCohortsInDatabase() {
		executeDataSet(COHORT_XML);
		
		// call the method
		List<Cohort> allCohorts = service.getAllCohorts();
		assertNotNull(allCohorts);
		// there is only one non-voided cohort in the data set
		assertEquals(1, allCohorts.size());
		assertFalse(allCohorts.get(0).getVoided());
	}