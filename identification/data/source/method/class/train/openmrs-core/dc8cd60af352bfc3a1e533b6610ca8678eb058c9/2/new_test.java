	@Test
	public void isActive_shouldReturnFalseIfStartDateIsAfterAsOfDate() throws Exception {
		CohortMembership newMember = new CohortMembership(4);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = dateFormat.parse("2017-01-01 00:00:00");
		newMember.setStartDate(startDate);
		
		Date dateToTest = dateFormat.parse("2016-12-01 00:00:00");
		assertFalse(newMember.isActive(dateToTest));
	}