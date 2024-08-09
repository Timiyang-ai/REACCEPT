	@Test
	public void union_shouldContainVoidedAndExpiredMemberships() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = dateFormat.parse("2017-01-01 00:00:00");
		Date endDate = dateFormat.parse("2017-02-01 00:00:00");

		Cohort cohortOne = new Cohort(3);
		CohortMembership membershipOne = new CohortMembership(7, startDate);
		membershipOne.setVoided(true);
		membershipOne.setEndDate(endDate);
		cohortOne.addMembership(membershipOne);

		Cohort cohortTwo = new Cohort(4);
		CohortMembership membershipTwo = new CohortMembership(8, startDate);
		membershipTwo.setVoided(true);
		membershipTwo.setEndDate(endDate);
		cohortTwo.addMembership(membershipTwo);

		Cohort cohortUnion = Cohort.union(cohortOne, cohortTwo);
		Collection<CohortMembership> unionOfMemberships = cohortUnion.getMemberships();
		unionOfMemberships.forEach(m -> {
			assertTrue(m.getPatientId().equals(7) || m.getPatientId().equals(8));
			assertTrue(m.getVoided() && m.getEndDate() != null);
		});
	}