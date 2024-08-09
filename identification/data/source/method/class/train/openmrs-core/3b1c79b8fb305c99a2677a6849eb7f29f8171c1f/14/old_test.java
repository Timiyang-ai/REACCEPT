	@Test
	public void getProgramByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "eae98b4c-e195-403b-b34a-82d94103b2c0";
		Program program = Context.getProgramWorkflowService().getProgramByUuid(uuid);
		Assert.assertEquals(1, (int) program.getProgramId());
	}