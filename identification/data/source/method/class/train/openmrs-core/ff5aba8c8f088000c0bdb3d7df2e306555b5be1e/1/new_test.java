@Test
	@Verifies(value = "should fail if patient program end date comes before its enrolled date", method = "validate(Object,Errors)")
	public void validate_shouldFailIfPatientProgramEndDateComesBeforeItsEnrolledDate() throws Exception {
		PatientProgram program = Context.getProgramWorkflowService().getPatientProgram(1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateEnrolled = sdf.parse("12/04/2014");
		Date dateCompleted = sdf.parse("21/03/2014");
		program.setDateEnrolled(dateEnrolled);
		program.setDateCompleted(dateCompleted);
		
		BindException errors = new BindException(program, "");
		new PatientProgramValidator().validate(program, errors);
		Assert.assertTrue(errors.hasFieldErrors("dateCompleted"));
	}