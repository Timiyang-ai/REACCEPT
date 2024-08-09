@Test
	public void testCancelWithSavepoint() throws Exception {
		{
			// Cancel with savepoint (no target directory)
			JobID jid = new JobID();

			OneShotLatch cancelWithSavepointLatch = new OneShotLatch();

			String[] parameters = { "-s", jid.toString() };
			TestingClusterClient<String> clusterClient = new TestingClusterClient<>();
			clusterClient.setCancelWithSavepointFunction((jobID, savepointDirectory) -> {
				assertNull(savepointDirectory);
				cancelWithSavepointLatch.trigger();
				return CompletableFuture.completedFuture(savepointDirectory);
			});
			MockedCliFrontend testFrontend = new MockedCliFrontend(clusterClient);
			testFrontend.cancel(parameters);
			cancelWithSavepointLatch.await();
		}

		{
			// Cancel with savepoint (with target directory)
			JobID jid = new JobID();

			OneShotLatch cancelWithSavepointLatch = new OneShotLatch();

			String[] parameters = { "-s", "targetDirectory", jid.toString() };
			TestingClusterClient<String> clusterClient = new TestingClusterClient<>();
			clusterClient.setCancelWithSavepointFunction((jobID, savepointDirectory) -> {
				assertNotNull(savepointDirectory);
				cancelWithSavepointLatch.trigger();
				return CompletableFuture.completedFuture(savepointDirectory);
			});
			MockedCliFrontend testFrontend = new MockedCliFrontend(clusterClient);
			testFrontend.cancel(parameters);
			cancelWithSavepointLatch.await();
		}
	}