@Test
	public void testCanBeSubsumed() throws Exception {
		PendingCheckpoint pending = createPendingCheckpoint();
		assertTrue(pending.canBeSubsumed());
	}