	@Test
	public void compareTo_shouldCompareMembershipsForEquality() {
		CohortMembership firstMembership = new CohortMembership(4);
		CohortMembership secondMembership = new CohortMembership(4);
		
		Cohort cohort = new Cohort(2);
		firstMembership.setCohort(cohort);
		secondMembership.setCohort(cohort);
		
		Date date = new Date();
		firstMembership.setStartDate(date);
		secondMembership.setStartDate(date);
		
		firstMembership.setUuid("same-uuid");
		secondMembership.setUuid("same-uuid");
		
		assertEquals(0, firstMembership.compareTo(secondMembership));
		assertEquals(0, secondMembership.compareTo(firstMembership));
	}