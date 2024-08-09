@Test
	public void testCanBeSubsumed() throws Exception {
		// Forced checkpoints cannot be subsumed
		CheckpointProperties forced = new CheckpointProperties(true, true, false, false, false, false, false);
		PendingCheckpoint pending = createPendingCheckpoint(forced, "ignored");
		assertFalse(pending.canBeSubsumed());

		try {
			pending.abortSubsumed();
			fail("Did not throw expected Exception");
		} catch (IllegalStateException ignored) {
			// Expected
		}

		// Non-forced checkpoints can be subsumed
		CheckpointProperties subsumed = new CheckpointProperties(false, true, false, false, false, false, false);
		pending = createPendingCheckpoint(subsumed, "ignored");
		assertTrue(pending.canBeSubsumed());
	}