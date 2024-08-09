@Test
	public void getCohort_shouldGetTheNonvoidedCohortIfTwoExistWithSameName() throws Exception {
		executeDataSet(COHORT_XML);
		
		// check to see if both cohorts have the same name and if one is voided
		List<Cohort> allCohorts = service.getAllCohorts(true);
		assertNotNull(allCohorts);
		assertEquals(allCohorts.get(0).getName(), allCohorts.get(1).getName());
		assertTrue(allCohorts.get(0).getVoided());
		assertFalse(allCohorts.get(1).getVoided());
		// the non-voided cohort should have an id of 2
		assertTrue(allCohorts.get(1).getCohortId() == 2);
		
		// ask for the cohort by name
		Cohort cohortToGet = service.getCohort("Example Cohort");
		// see if the non-voided one got returned
		assertTrue(cohortToGet.getCohortId() == 2);
	}