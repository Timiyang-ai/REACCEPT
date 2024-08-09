@Test
	public void testReplace() throws Exception {
		// Setup
		LongStateStorage stateHandleProvider = new LongStateStorage();

		ZooKeeperStateHandleStore<Long> store = new ZooKeeperStateHandleStore<>(
				ZooKeeper.getClient(), stateHandleProvider);

		// Config
		final String pathInZooKeeper = "/testReplace";
		final Long initialState = 30968470898L;
		final Long replaceState = 88383776661L;

		// Test
		store.add(pathInZooKeeper, initialState);
		store.replace(pathInZooKeeper, 0, replaceState);

		// Verify
		// State handles created
		assertEquals(2, stateHandleProvider.getStateHandles().size());
		assertEquals(initialState, stateHandleProvider.getStateHandles().get(0).getState(null));
		assertEquals(replaceState, stateHandleProvider.getStateHandles().get(1).getState(null));

		// Path created and is persistent
		Stat stat = ZooKeeper.getClient().checkExists().forPath(pathInZooKeeper);
		assertNotNull(stat);
		assertEquals(0, stat.getEphemeralOwner());

		// Data is equal
		@SuppressWarnings("unchecked")
		Long actual = ((StateHandle<Long>) InstantiationUtil.deserializeObject(
				ZooKeeper.getClient().getData().forPath(pathInZooKeeper),
				ClassLoader.getSystemClassLoader())).getState(null);

		assertEquals(replaceState, actual);
	}