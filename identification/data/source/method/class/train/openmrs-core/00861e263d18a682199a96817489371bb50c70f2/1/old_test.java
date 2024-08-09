@Test
	@Verifies(value = "should fail if the start date for any patient state is null and is not the first", method = "validate(Object,Errors)")
	public void validate_shouldFailIfTheStartDateForAnyPatientStateIsNullAndIsNotTheFirst() throws Exception {
		PatientProgram program = Context.getProgramWorkflowService().getPatientProgram(1);
		PatientState firstState = program.getStates().iterator().next();
		
		//Add a state that comes after patientState1 with a null date
		PatientState newPatientState = new PatientState();
		//set the state to be that of the current state 
		newPatientState.setState(firstState.getState().getProgramWorkflow().getState(4));
		Assert.assertNotSame(firstState.getState(), newPatientState.getState());//sanity check
		program.getStates().add(newPatientState);
		
		BindException errors = new BindException(program, "");
		new PatientProgramValidator().validate(program, errors);
		Assert.assertEquals(true, errors.hasFieldErrors("states"));
	}