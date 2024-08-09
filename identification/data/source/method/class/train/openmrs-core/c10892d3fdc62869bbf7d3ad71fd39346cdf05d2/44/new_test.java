	@Test
	public void saveCohort_shouldCreateNewCohorts() {
		executeDataSet(COHORT_XML);
		
		// make sure we have two cohorts
		List<Cohort> allCohorts = service.getAllCohorts(true);
		assertNotNull(allCohorts);
		assertEquals(2, allCohorts.size());
		
		// make and save a new one
		Integer[] ids = { 2, 3 };
		Cohort newCohort = new Cohort("a third cohort", "a  cohort to add for testing", ids);
		service.saveCohort(newCohort);
		
		// see if the new cohort shows up in the list of cohorts
		allCohorts = service.getAllCohorts(true);
		assertNotNull(allCohorts);
		assertEquals(3, allCohorts.size());
	}