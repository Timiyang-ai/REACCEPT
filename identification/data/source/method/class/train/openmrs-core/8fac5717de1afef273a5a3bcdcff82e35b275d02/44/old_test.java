	@Test
	public void retireProgram_shouldSaveTheRetiredProgramWithReason() throws APIException {
		String reason = "Feeling well.";
		
		String uuid = "eae98b4c-e195-403b-b34a-82d94103b2c0";
		Program program = Context.getProgramWorkflowService().getProgramByUuid(uuid);
		
		Program retireProgram = pws.retireProgram(program, reason);
		
		assertTrue(retireProgram.getRetired());
		assertEquals(reason, retireProgram.getRetireReason());
		for (ProgramWorkflow programWorkflow : program.getAllWorkflows()) {
			assertTrue(programWorkflow.getRetired());
			for (ProgramWorkflowState programWorkflowState : programWorkflow.getStates()) {
				assertTrue(programWorkflowState.getRetired());
			}
		}
		
	}