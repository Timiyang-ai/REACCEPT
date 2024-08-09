@Test
	@Verifies(value = "should remove membership from cohort", method = "removeMemberShipFromCohort(Cohort, CohortMembership)")
	public void removeMembershipFromCohort_shouldRemoveMembershipFromCohort() throws Exception {
		executeDataSet(COHORT_XML);

		CohortMembership memberToAddThenRemove = new CohortMembership(new Patient(4));
		service.addMembershipToCohort(service.getCohort(1), memberToAddThenRemove);
		assertTrue(service.getCohort(1).contains(memberToAddThenRemove.getPatient()));
		assertNull(memberToAddThenRemove.getEndDate());

		service.removeMemberShipFromCohort(service.getCohort(1), memberToAddThenRemove);
		assertNotNull(memberToAddThenRemove.getEndDate());
	}