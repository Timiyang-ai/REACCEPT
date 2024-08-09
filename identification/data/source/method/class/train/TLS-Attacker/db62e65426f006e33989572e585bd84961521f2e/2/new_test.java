@Test
    public void testCommit() {

	Result result = new Result(true, true, 0, System.currentTimeMillis(), new BranchTrace(), new TestVector(
		new WorkflowTrace(), null, null), new TestVector(new WorkflowTrace(), null, null), "test.unit");// TODO
														// Delete
														// Testfiles
	ResultContainer instance = ResultContainer.getInstance();
	instance.commit(result);

    }