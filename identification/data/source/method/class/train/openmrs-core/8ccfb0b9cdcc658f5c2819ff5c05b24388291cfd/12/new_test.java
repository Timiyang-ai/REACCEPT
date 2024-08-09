@Test
	public void purgeCohort_shouldDeleteCohortFromDatabase() throws Exception {
		executeDataSet(COHORT_XML);
		List<Cohort> allCohorts = service.getAllCohorts(true);
		assertEquals(2, allCohorts.size());
		service.purgeCohort(allCohorts.get(0));
		allCohorts = service.getAllCohorts(true);
		assertEquals(1, allCohorts.size());
	}