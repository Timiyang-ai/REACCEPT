@Test
	public void testRecover() throws Exception {
		CompletedCheckpointStore checkpoints = createCompletedCheckpoints(3);
		SharedStateRegistry sharedStateRegistry = new SharedStateRegistry();

		TestCompletedCheckpoint[] expected = new TestCompletedCheckpoint[] {
				createCheckpoint(0), createCheckpoint(1), createCheckpoint(2)
		};

		// Add multiple checkpoints
		checkpoints.addCheckpoint(expected[0], sharedStateRegistry);
		checkpoints.addCheckpoint(expected[1], sharedStateRegistry);
		checkpoints.addCheckpoint(expected[2], sharedStateRegistry);

		verifyCheckpointRegistered(expected[0].getTaskStates().values(), sharedStateRegistry);
		verifyCheckpointRegistered(expected[1].getTaskStates().values(), sharedStateRegistry);
		verifyCheckpointRegistered(expected[2].getTaskStates().values(), sharedStateRegistry);

		// All three should be in ZK
		assertEquals(3, ZooKeeper.getClient().getChildren().forPath(CheckpointsPath).size());
		assertEquals(3, checkpoints.getNumberOfRetainedCheckpoints());

		resetCheckpoint(expected[0].getTaskStates().values());
		resetCheckpoint(expected[1].getTaskStates().values());
		resetCheckpoint(expected[2].getTaskStates().values());

		// Recover
		SharedStateRegistry newSharedStateRegistry = new SharedStateRegistry();
		checkpoints.recover(newSharedStateRegistry);

		assertEquals(3, ZooKeeper.getClient().getChildren().forPath(CheckpointsPath).size());
		assertEquals(3, checkpoints.getNumberOfRetainedCheckpoints());
		assertEquals(expected[2], checkpoints.getLatestCheckpoint());

		List<CompletedCheckpoint> expectedCheckpoints = new ArrayList<>(3);
		expectedCheckpoints.add(expected[1]);
		expectedCheckpoints.add(expected[2]);
		expectedCheckpoints.add(createCheckpoint(3));

		checkpoints.addCheckpoint(expectedCheckpoints.get(2), newSharedStateRegistry);

		List<CompletedCheckpoint> actualCheckpoints = checkpoints.getAllCheckpoints();

		assertEquals(expectedCheckpoints, actualCheckpoints);

		for (CompletedCheckpoint actualCheckpoint : actualCheckpoints) {
			verifyCheckpointRegistered(actualCheckpoint.getTaskStates().values(), newSharedStateRegistry);
		}
	}