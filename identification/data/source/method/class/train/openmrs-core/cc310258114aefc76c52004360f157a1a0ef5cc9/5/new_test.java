	@Test
	public void endCohortMembership_shouldEndTheCohortMembership() {
		Date endOnDate = new Date();
		executeDataSet(COHORT_XML);
		Cohort cohort = service.getCohort(1);
		CohortMembership cm = cohort.getActiveMemberships().iterator().next();
		assertNull(cm.getEndDate());
		service.endCohortMembership(cm, endOnDate);
		assertEquals(endOnDate, cm.getEndDate());
		// Since TRUNK-5450 also CohortMembers with an end-date are taken into account by contains
		assertTrue(cohort.contains(cm.getPatientId()));
	}