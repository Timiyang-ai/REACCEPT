	@Test
	public void clearAll() throws Exception {
		ResultPartitionID partitionId = new ResultPartitionID();
		TaskEventDispatcher ted = new TaskEventDispatcher();
		ted.registerPartition(partitionId);

		//noinspection unchecked
		ZeroShotEventListener eventListener1 = new ZeroShotEventListener();
		ted.subscribeToEvent(partitionId, eventListener1, AllWorkersDoneEvent.class);

		ted.clearAll();

		assertFalse(ted.publish(partitionId, new AllWorkersDoneEvent()));
	}