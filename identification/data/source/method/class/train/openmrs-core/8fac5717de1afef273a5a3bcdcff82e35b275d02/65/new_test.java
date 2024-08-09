	@Test
	public void getWorkflow_shouldGetWorkflowAssociatedWithGivenIdIfWorkflowIdExists() {
		
		final Integer EXISTING_WORKFLOW_ID = 1;
		
		ProgramWorkflow workflow = pws.getWorkflow(EXISTING_WORKFLOW_ID);
		
		assertNotNull("ProgramWorkflow not found", workflow);
		assertThat(workflow.getId(), is(EXISTING_WORKFLOW_ID));
	}