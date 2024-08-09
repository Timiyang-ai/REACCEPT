public void remove(String pathInZooKeeper, BackgroundCallback callback) throws Exception {
		checkNotNull(pathInZooKeeper, "Path in ZooKeeper");
		checkNotNull(callback, "Background callback");

		client.delete().deletingChildrenIfNeeded().inBackground(callback).forPath(pathInZooKeeper);
	}