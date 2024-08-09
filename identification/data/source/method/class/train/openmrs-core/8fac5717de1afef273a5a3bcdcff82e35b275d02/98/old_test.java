	@Test
	public void getCohort_shouldOnlyGetNonVoidedCohortsByName() {
		executeDataSet(COHORT_XML);
		
		// make sure we have two cohorts with the same name and the first is voided
		List<Cohort> allCohorts = service.getAllCohorts(true);
		assertNotNull(allCohorts);
		assertEquals(2, allCohorts.size());
		assertTrue(allCohorts.get(0).getVoided());
		assertFalse(allCohorts.get(1).getVoided());
		
		// now do the actual test: getCohort by name and expect a non voided cohort
		Cohort exampleCohort = service.getCohortByName("Example Cohort");
		assertNotNull(exampleCohort);
		// since TRUNK-5450 also non-active cohorts (with an end-date) are counted
		assertEquals(2, exampleCohort.size());
		assertFalse(exampleCohort.getVoided());
	}