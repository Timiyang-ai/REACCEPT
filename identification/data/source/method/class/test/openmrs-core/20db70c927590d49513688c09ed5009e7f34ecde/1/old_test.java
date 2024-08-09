	@Test
	public void validate_shouldFailIfThePatientFieldIsBlank() {
		PatientProgram program = new PatientProgram();
		BindException errors = new BindException(program, "program");
		new PatientProgramValidator().validate(program, errors);
		Assert.assertEquals(true, errors.hasFieldErrors("patient"));
	}