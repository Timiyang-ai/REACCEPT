@Test
	public void testDiscardAllCheckpoints() throws Exception {
		CompletedCheckpointStore checkpoints = createCompletedCheckpoints(4, userClassLoader);

		TestCheckpoint[] expected = new TestCheckpoint[] {
				createCheckpoint(0), createCheckpoint(1),
				createCheckpoint(2), createCheckpoint(3)
		};

		for (TestCheckpoint checkpoint : expected) {
			checkpoints.addCheckpoint(checkpoint);
		}

		checkpoints.shutdown();

		// Empty state
		assertNull(checkpoints.getLatestCheckpoint());
		assertEquals(0, checkpoints.getAllCheckpoints().size());
		assertEquals(0, checkpoints.getNumberOfRetainedCheckpoints());

		// All have been discarded
		for (TestCheckpoint checkpoint : expected) {
			// The ZooKeeper implementation discards asynchronously
			checkpoint.awaitDiscard();
			assertTrue(checkpoint.isDiscarded());
			assertEquals(userClassLoader, checkpoint.getDiscardClassLoader());
		}
	}