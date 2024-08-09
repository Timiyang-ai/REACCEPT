@Test
	public void testAdd() throws Exception {
		// Setup
		LongStateHandleProvider stateHandleProvider = new LongStateHandleProvider();

		ZooKeeperStateHandleStore<Long> store = new ZooKeeperStateHandleStore<>(
				ZooKeeper.getClient(), stateHandleProvider);

		// Config
		final String pathInZooKeeper = "/testAdd";
		final Long state = 1239712317L;

		// Test
		store.add(pathInZooKeeper, state);

		// Verify
		// State handle created
		assertEquals(1, stateHandleProvider.getStateHandles().size());
		assertEquals(state, stateHandleProvider.getStateHandles().get(0).getState(null));

		// Path created and is persistent
		Stat stat = ZooKeeper.getClient().checkExists().forPath(pathInZooKeeper);
		assertNotNull(stat);
		assertEquals(0, stat.getEphemeralOwner());

		// Data is equal
		@SuppressWarnings("unchecked")
		Long actual = ((StateHandle<Long>) InstantiationUtil.deserializeObject(
				ZooKeeper.getClient().getData().forPath(pathInZooKeeper),
				ClassLoader.getSystemClassLoader())).getState(null);

		assertEquals(state, actual);
	}