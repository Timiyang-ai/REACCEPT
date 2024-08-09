@Test
	@Verifies(value = "should fail if patient program end date comes before its enrolled date", method = "validate(Object,Errors)")
	public void validate_shouldFailIfPatientProgramEndDateComesBeforeItsEnrolledDate() throws Exception {
		PatientProgram program = Context.getProgramWorkflowService().getPatientProgram(1);
		Calendar c = Calendar.getInstance();
		c.set(2014, 3, 1);//set to an old date
		program.setDateEnrolled(new Date());
		program.setDateCompleted(c.getTime());
		
		BindException errors = new BindException(program, "");
		new PatientProgramValidator().validate(program, errors);
		Assert.assertTrue(errors.hasErrors());
	}