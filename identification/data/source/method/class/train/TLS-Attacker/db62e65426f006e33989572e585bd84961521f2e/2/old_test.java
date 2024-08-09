@Test
    public void testCommit() {

	Result result = new Result(true, true, 0, System.currentTimeMillis(), new BranchTrace(), new WorkflowTrace(),
		new WorkflowTrace(), "test.unit");
	ResultContainer instance = ResultContainer.getInstance();
	instance.commit(result);

    }