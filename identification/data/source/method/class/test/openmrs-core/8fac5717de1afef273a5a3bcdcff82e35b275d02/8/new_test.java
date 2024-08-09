	@Test
	public void getState_shouldGetStateAssociatedWithGivenIdIfWorkflowStateIdExists() {
		
		final Integer EXISTING_WORKFLOW_STATE_ID = 1;
		
		ProgramWorkflowState state = pws.getState(EXISTING_WORKFLOW_STATE_ID);
		
		assertNotNull("ProgramWorkflowState not found", state);
		assertThat(state.getId(), is(EXISTING_WORKFLOW_STATE_ID));
	}