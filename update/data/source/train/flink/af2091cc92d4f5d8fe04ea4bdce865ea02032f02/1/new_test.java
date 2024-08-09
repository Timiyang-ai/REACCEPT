@Test
	public void testRecover() throws Exception {
		CompletedCheckpointStore checkpoints = createCompletedCheckpoints(3, ClassLoader
				.getSystemClassLoader());

		TestCheckpoint[] expected = new TestCheckpoint[] {
				createCheckpoint(0), createCheckpoint(1), createCheckpoint(2)
		};

		// Add multiple checkpoints
		checkpoints.addCheckpoint(expected[0]);
		checkpoints.addCheckpoint(expected[1]);
		checkpoints.addCheckpoint(expected[2]);

		// All three should be in ZK
		assertEquals(3, ZooKeeper.getClient().getChildren().forPath(CheckpointsPath).size());
		assertEquals(3, checkpoints.getNumberOfRetainedCheckpoints());

		// Recover
		checkpoints.recover();

		// Only the latest one should be in ZK
		Deadline deadline = new FiniteDuration(1, TimeUnit.MINUTES).fromNow();

		// Retry this operation, because removal is asynchronous
		while (deadline.hasTimeLeft() && ZooKeeper.getClient()
				.getChildren().forPath(CheckpointsPath).size() != 1) {

			Thread.sleep(Math.min(100, deadline.timeLeft().toMillis()));
		}

		assertEquals(1, ZooKeeper.getClient().getChildren().forPath(CheckpointsPath).size());
		assertEquals(1, checkpoints.getNumberOfRetainedCheckpoints());
		assertEquals(expected[2], checkpoints.getLatestCheckpoint());
	}