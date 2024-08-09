	@Test
	public void addToDate_shouldAddSecondsWhenUnitIsSeconds() throws ParseException {
		Duration duration = new Duration(30, Duration.SNOMED_CT_SECONDS_CODE);
		
		Date autoExpireDate = duration.addToDate(createDateTime("2014-07-01 10:00:00"), null);
		
		assertEquals(createDateTime("2014-07-01 10:00:30"), autoExpireDate);
	}