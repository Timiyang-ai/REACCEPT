@Test
	public void getMemberships_shouldReturnUnvoidedMemberships() throws Exception {
		executeDataSet(COHORT_XML);
		
		Cohort cohort = Context.getCohortService().getCohort(1);
		
		CohortMembership nonVoidedMembership = new CohortMembership(4);
		CohortMembership voidedMembership = new CohortMembership(7);
		voidedMembership.setVoided(true);
		voidedMembership.setVoidedBy(Context.getAuthenticatedUser());
		voidedMembership.setDateVoided(new Date());
		voidedMembership.setVoidReason("Void reason");
		
		cohort.addMembership(nonVoidedMembership);
		cohort.addMembership(voidedMembership);
		
		Context.getCohortService().saveCohort(cohort);
		Collection<CohortMembership> unvoidedMemberships = cohort.getMemberships(false);
		assertEquals(2, unvoidedMemberships.size());
	}