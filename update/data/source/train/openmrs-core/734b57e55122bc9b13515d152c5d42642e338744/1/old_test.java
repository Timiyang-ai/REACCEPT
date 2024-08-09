@Test
	@Verifies(value = "should fail if patient is not set", method = "validate(Object,Errors)")
	public void validate_shouldFailIfPatientIsNotSet() throws Exception {
		VisitService vs = Context.getVisitService();
		Visit visit = new Visit();
		visit.setVisitType(vs.getVisitType(1));
		visit.setStartDatetime(new Date());
		Errors errors = new BindException(visit, "visit");
		new VisitValidator().validate(visit, errors);
		Assert.assertTrue(errors.hasFieldErrors("patient"));
	}