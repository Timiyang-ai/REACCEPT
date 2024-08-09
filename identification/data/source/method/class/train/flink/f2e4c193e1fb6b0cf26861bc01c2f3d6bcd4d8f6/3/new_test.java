@Test
	public void testGetAllSortedByName() throws Exception {
		// Setup
		LongStateStorage stateHandleProvider = new LongStateStorage();

		ZooKeeperStateHandleStore<Long> store = new ZooKeeperStateHandleStore<>(
				ZooKeeper.getClient(), stateHandleProvider, TestExecutors.directExecutor());

		// Config
		final String pathInZooKeeper = "/testGetAllSortedByName";

		final Long[] expected = new Long[] {
				311222268470898L, 132812888L, 27255442L, 11122233124L };

		// Test
		for (long val : expected) {
			store.add(pathInZooKeeper, val, CreateMode.PERSISTENT_SEQUENTIAL);
		}

		List<Tuple2<StateHandle<Long>, String>> actual = store.getAllSortedByName();
		assertEquals(expected.length, actual.size());

		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual.get(i).f0.getState(null));
		}
	}