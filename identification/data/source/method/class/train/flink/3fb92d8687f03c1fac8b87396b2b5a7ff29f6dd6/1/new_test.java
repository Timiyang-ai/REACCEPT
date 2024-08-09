@Test
	public void testRemove() throws Exception {
		// Setup
		LongStateStorage stateHandleProvider = new LongStateStorage();

		ZooKeeperStateHandleStore<Long> store = new ZooKeeperStateHandleStore<>(
				ZooKeeper.getClient(), stateHandleProvider, Executors.directExecutor());

		// Config
		final String pathInZooKeeper = "/testRemove";
		final Long state = 27255442L;

		store.add(pathInZooKeeper, state);

		// Test
		store.remove(pathInZooKeeper);

		// Verify discarded
		assertEquals(0, ZooKeeper.getClient().getChildren().forPath("/").size());
	}