@Test
	public void testRelease() throws Exception {
		LongStateStorage longStateStorage = new LongStateStorage();

		ZooKeeperStateHandleStore<Long> zkStore = new ZooKeeperStateHandleStore<>(
			ZOOKEEPER.getClient(),
			longStateStorage);

		final String path = "/state";

		zkStore.addAndLock(path, 42L);

		final String lockPath = zkStore.getLockPath(path);

		Stat stat = ZOOKEEPER.getClient().checkExists().forPath(lockPath);

		assertNotNull("Expected an existing lock", stat);

		zkStore.release(path);

		stat = ZOOKEEPER.getClient().checkExists().forPath(path);

		// release should have removed the lock child
		assertEquals("Expected no lock nodes as children", 0, stat.getNumChildren());

		zkStore.releaseAndTryRemove(path);

		stat = ZOOKEEPER.getClient().checkExists().forPath(path);

		assertNull("State node should have been removed.",stat);
	}