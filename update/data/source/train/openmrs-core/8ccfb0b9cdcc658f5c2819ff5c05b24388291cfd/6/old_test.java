@Test
	@Verifies(value = "should add membership to cohort", method = "addMembershipToCohort(Cohort, CohortMembership)")
	public void addMembershipToCohort_shouldAddMembershipToCohort() throws Exception {
		executeDataSet(COHORT_XML);
		
		Patient p = new Patient(4);
		CohortMembership memberToAdd = new CohortMembership(p);
		service.addMembershipToCohort(service.getCohort(1), memberToAdd);
		assertTrue(service.getCohort(1).contains(p));
	}