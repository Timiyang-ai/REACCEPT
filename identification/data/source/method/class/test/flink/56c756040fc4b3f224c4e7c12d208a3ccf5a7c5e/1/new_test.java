	@Test
	public void dispose() throws Exception {
		final int chkCount = 3;
		final int confirmed = chkCount - 1;
		List<TaskStateSnapshot> taskStateSnapshots = storeStates(chkCount);
		taskLocalStateStore.confirmCheckpoint(confirmed);
		taskLocalStateStore.dispose();

		checkPrunedAndDiscarded(taskStateSnapshots, 0, chkCount);
	}