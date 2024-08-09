@Test
	public void getCohortMemberships_shouldGetMembershipsContainingPatient() throws Exception {
		executeDataSet(COHORT_XML);
		List<CohortMembership> memberships = service.getCohortMemberships(6, null, false);
		assertThat(memberships.size(), is(1));
		assertThat(memberships.get(0).getCohort().getCohortId(), is(2));
	}