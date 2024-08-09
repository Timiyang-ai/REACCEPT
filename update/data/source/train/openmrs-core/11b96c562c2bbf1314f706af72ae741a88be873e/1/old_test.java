@Test
	@Verifies(value = "should fail if the endDatetime is before the startDatetime", method = "validate(Object,Errors)")
	public void validate_shouldFailIfTheEndDatetimeIsBeforeTheStartDatetime() throws Exception {
		Visit visit = new Visit();
		Calendar c = Calendar.getInstance();
		visit.setStartDatetime(c.getTime());
		c.set(2010, 3, 15);//set to an older date
		visit.setStopDatetime(c.getTime());
		Errors errors = new BindException(visit, "visit");
		new VisitValidator().validate(visit, errors);
		Assert.assertEquals(true, errors.hasFieldErrors("startDatetime"));
	}