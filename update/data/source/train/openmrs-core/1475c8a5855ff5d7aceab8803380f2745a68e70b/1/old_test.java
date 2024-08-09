@Test
	public void voidLastState_shouldVoidStateWithEndDateNullIfStartDatesEqual() {
		//given
		PatientProgram program = new PatientProgram();
		ProgramWorkflow workflow = new ProgramWorkflow();
		ProgramWorkflowState workflowState = new ProgramWorkflowState();
		workflowState.setProgramWorkflow(workflow);
		
		Date startDate = new Date();
		
		PatientState state1 = new PatientState();
		state1.setStartDate(startDate);
		state1.setEndDate(null);
		state1.setState(workflowState);
		
		PatientState state2 = new PatientState();
		state2.setStartDate(startDate);
		state2.setEndDate(new Date());
		state2.setState(workflowState);
		
		program.getStates().add(state1);
		program.getStates().add(state2);
		
		//when
		program.voidLastState(workflow, new User(), new Date(), "");
		
		//then
		Assert.assertTrue(state1.getVoided());
		Assert.assertFalse(state2.getVoided());
	}