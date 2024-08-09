@Test
	@Verifies(value = "should fail if any patient state has an end date before its start date", method = "validate(Object,Errors)")
	public void validate_shouldFailIfAnyPatientStateHasAnEndDateBeforeItsStartDate() throws Exception {
		PatientProgram program = Context.getProgramWorkflowService().getPatientProgram(1);
		PatientState patientSate = program.getStates().iterator().next();
		Assert.assertNotNull(patientSate);
		Calendar c = Calendar.getInstance();
		patientSate.setStartDate(c.getTime());
		c.set(1970, 2, 1);//set to an old date
		patientSate.setEndDate(c.getTime());
		
		BindException errors = new BindException(program, "");
		new PatientProgramValidator().validate(program, errors);
		Assert.assertTrue(errors.hasErrors());
	}