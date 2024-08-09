@Test
	public void getMemberships_shouldGetMembershipsAsOfADate() throws Exception {
		executeDataSet(COHORT_XML);

		Cohort cohort = Context.getCohortService().getCohort(1);

		CohortMembership newMember = new CohortMembership(4);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateToTest = dateFormat.parse("2016-11-01 00:00:00");
		newMember.setStartDate(dateToTest);
		service.addMembershipToCohort(cohort, newMember);

		List<CohortMembership> membersAsOfDate = cohort.getActiveMemberships(dateToTest);
		assertFalse(membersAsOfDate.isEmpty());
		assertTrue(membersAsOfDate.stream().anyMatch(m -> m.getStartDate().equals(dateToTest)));
	}