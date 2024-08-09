@Test
	public void testReleaseAll() throws Exception {
		LongStateStorage longStateStorage = new LongStateStorage();

		ZooKeeperStateHandleStore<Long> zkStore = new ZooKeeperStateHandleStore<>(
			ZOOKEEPER.getClient(),
			longStateStorage,
			Executors.directExecutor());

		final Collection<String> paths = Arrays.asList("/state1", "/state2", "/state3");

		for (String path : paths) {
			zkStore.addAndLock(path, 42L);
		}

		for (String path : paths) {
			Stat stat = ZOOKEEPER.getClient().checkExists().forPath(zkStore.getLockPath(path));

			assertNotNull("Expecte and existing lock.", stat);
		}

		zkStore.releaseAll();

		for (String path : paths) {
			Stat stat = ZOOKEEPER.getClient().checkExists().forPath(path);

			assertEquals(0, stat.getNumChildren());
		}

		zkStore.releaseAndTryRemoveAll();

		Stat stat = ZOOKEEPER.getClient().checkExists().forPath("/");

		assertEquals(0, stat.getNumChildren());
	}