@Test
	public void testGetAll() throws Exception {
		// Setup
		LongStateStorage stateHandleProvider = new LongStateStorage();

		ZooKeeperStateHandleStore<Long> store = new ZooKeeperStateHandleStore<>(
				ZooKeeper.getClient(), stateHandleProvider, TestExecutors.directExecutor());

		// Config
		final String pathInZooKeeper = "/testGetAll";

		final Set<Long> expected = new HashSet<>();
		expected.add(311222268470898L);
		expected.add(132812888L);
		expected.add(27255442L);
		expected.add(11122233124L);

		// Test
		for (long val : expected) {
			store.add(pathInZooKeeper, val, CreateMode.PERSISTENT_SEQUENTIAL);
		}

		for (Tuple2<StateHandle<Long>, String> val : store.getAll()) {
			assertTrue(expected.remove(val.f0.getState(null)));
		}
		assertEquals(0, expected.size());
	}