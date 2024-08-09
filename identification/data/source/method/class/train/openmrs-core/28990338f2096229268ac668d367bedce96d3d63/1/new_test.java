	@Test
	public void hasNoActiveMemberships_shouldReturnTrueIfNoneExists() throws Exception{
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		// endDateLater will be tomorrow		
		Date endDateLater =  calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, -2);
		// endDateEarlier will be yesterday
		Date endDateEarlier = calendar.getTime();
		
		Cohort cohort = new Cohort(3);
		CohortMembership temp = new CohortMembership(7);
		temp.setVoided(true);
		temp.setEndDate(endDateLater);
		cohort.addMembership(temp);
		
		temp = new CohortMembership(8);
		temp.setVoided(true);
		cohort.addMembership(temp);
		
		temp = new CohortMembership(9);
		temp.setEndDate(endDateEarlier);
		cohort.addMembership(temp);
		
		temp = new CohortMembership(10);
		temp.setVoided(true);
		cohort.addMembership(temp);
		
		assertTrue(cohort.hasNoActiveMemberships());
	}