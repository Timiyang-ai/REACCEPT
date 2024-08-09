@Test
	public void compareTo_shouldFailIfStartOrEndDateDoNotMatch() throws Exception {
		CohortMembership firstMembership = new CohortMembership(4);
		CohortMembership secondMembership = new CohortMembership(4);

		Cohort cohort = new Cohort(1);

		firstMembership.setCohort(cohort);
		secondMembership.setCohort(cohort);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date oneDate = dateFormat.parse("2017-01-01 00:00:00");
		Date twoDate = dateFormat.parse("2017-01-31 00:00:00");

		firstMembership.setStartDate(oneDate);
		secondMembership.setStartDate(twoDate);

		assertEquals(firstMembership.compareTo(secondMembership), -1);

		secondMembership.setStartDate(oneDate);
		secondMembership.setEndDate(twoDate);

		assertEquals(firstMembership.compareTo(secondMembership), -1);
	}