	@Test
	public void getWorkflowByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "84f0effa-dd73-46cb-b931-7cd6be6c5f81";
		ProgramWorkflow programWorkflow = Context.getProgramWorkflowService().getWorkflowByUuid(uuid);
		Assert.assertEquals(1, (int) programWorkflow.getProgramWorkflowId());
	}