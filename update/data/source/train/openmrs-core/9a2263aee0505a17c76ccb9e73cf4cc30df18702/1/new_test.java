@Test
	@Verifies(value = "should fail if any patient state has a null work flow state", method = "validate(Object,Errors)")
	public void validate_shouldFailIfAnyPatientStateHasANullWorkFlowState() throws Exception {
		PatientProgram program = Context.getProgramWorkflowService().getPatientProgram(1);
		PatientState patientState = program.getStates().iterator().next();
		patientState.setState(null);
		
		BindException errors = new BindException(program, "");
		new PatientProgramValidator().validate(program, errors);
		Assert.assertTrue(errors.hasFieldErrors("states"));
	}