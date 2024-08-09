@Test
	@Verifies(value = "should fail if there is more than one state with a null start date in the same workflow", method = "validate(Object,Errors)")
	public void validate_shouldFailIfThereIsMoreThanOneStateWithANullStartDateInTheSameWorkflow() throws Exception {
		ProgramWorkflowService pws = Context.getProgramWorkflowService();
		Patient patient = Context.getPatientService().getPatient(6);
		PatientProgram pp = new PatientProgram();
		pp.setPatient(patient);
		pp.setProgram(pws.getProgram(1));
		ProgramWorkflow testWorkflow = pp.getProgram().getWorkflow(1);
		
		//Add 2 other patient states with null start date
		PatientState newPatientState1 = new PatientState();
		newPatientState1.setState(testWorkflow.getState(1));
		pp.getStates().add(newPatientState1);
		
		PatientState newPatientState2 = new PatientState();
		newPatientState2.setState(testWorkflow.getState(2));
		pp.getStates().add(newPatientState2);
		
		BindException errors = new BindException(pp, "");
		new PatientProgramValidator().validate(pp, errors);
		Assert.assertEquals(true, errors.hasFieldErrors("states"));
	}