@Test
	public void addMembershipToCohort_shouldAddMembershipToCohort() throws Exception {
		executeDataSet(COHORT_XML);
		
		Patient p = new Patient(4);
		CohortMembership memberToAdd = new CohortMembership(p.getPatientId());
		Cohort cohort = service.getCohort(1);
		if (!cohort.contains(p)) {
			service.addMembershipToCohort(cohort, memberToAdd);
		}
		assertTrue(cohort.contains(p));
	}