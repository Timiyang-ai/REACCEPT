@Test
	public void testRecover() throws Exception {
		AbstractCompletedCheckpointStore checkpoints = createCompletedCheckpoints(3);

		TestCompletedCheckpoint[] expected = new TestCompletedCheckpoint[] {
				createCheckpoint(0), createCheckpoint(1), createCheckpoint(2)
		};

		// Add multiple checkpoints
		checkpoints.addCheckpoint(expected[0]);
		checkpoints.addCheckpoint(expected[1]);
		checkpoints.addCheckpoint(expected[2]);

		verifyCheckpointRegistered(expected[0].getOperatorStates().values(), checkpoints.sharedStateRegistry);
		verifyCheckpointRegistered(expected[1].getOperatorStates().values(), checkpoints.sharedStateRegistry);
		verifyCheckpointRegistered(expected[2].getOperatorStates().values(), checkpoints.sharedStateRegistry);

		// All three should be in ZK
		assertEquals(3, ZOOKEEPER.getClient().getChildren().forPath(CHECKPOINT_PATH).size());
		assertEquals(3, checkpoints.getNumberOfRetainedCheckpoints());

		// Recover
		checkpoints.recover();

		assertEquals(3, ZOOKEEPER.getClient().getChildren().forPath(CHECKPOINT_PATH).size());
		assertEquals(3, checkpoints.getNumberOfRetainedCheckpoints());
		assertEquals(expected[2], checkpoints.getLatestCheckpoint());

		List<CompletedCheckpoint> expectedCheckpoints = new ArrayList<>(3);
		expectedCheckpoints.add(expected[1]);
		expectedCheckpoints.add(expected[2]);
		expectedCheckpoints.add(createCheckpoint(3));

		checkpoints.addCheckpoint(expectedCheckpoints.get(2));

		List<CompletedCheckpoint> actualCheckpoints = checkpoints.getAllCheckpoints();

		assertEquals(expectedCheckpoints, actualCheckpoints);

		for (CompletedCheckpoint actualCheckpoint : actualCheckpoints) {
			verifyCheckpointRegistered(actualCheckpoint.getOperatorStates().values(), checkpoints.sharedStateRegistry);
		}
	}