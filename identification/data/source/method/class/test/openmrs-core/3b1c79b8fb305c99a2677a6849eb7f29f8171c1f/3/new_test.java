	@Test
	public void getStateByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "92584cdc-6a20-4c84-a659-e035e45d36b0";
		ProgramWorkflowState state = Context.getProgramWorkflowService().getStateByUuid(uuid);
		Assert.assertEquals(1, (int) state.getProgramWorkflowStateId());
	}