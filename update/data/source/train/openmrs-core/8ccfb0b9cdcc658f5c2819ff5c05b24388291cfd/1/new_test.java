@Test
	public void removeMembershipFromCohort_shouldRemoveMembershipFromCohort() throws Exception {
		executeDataSet(COHORT_XML);

		CohortMembership memberToAddThenRemove = new CohortMembership(4);
		service.addMembershipToCohort(service.getCohort(1), memberToAddThenRemove);
		assertTrue(service.getCohort(1).contains(memberToAddThenRemove.getPatientId()));
		assertNull(memberToAddThenRemove.getEndDate());

		service.removeMembershipFromCohort(service.getCohort(1), memberToAddThenRemove);
		assertNotNull(memberToAddThenRemove.getEndDate());
		assertFalse(service.getCohort(1).contains(memberToAddThenRemove.getPatientId()));
	}