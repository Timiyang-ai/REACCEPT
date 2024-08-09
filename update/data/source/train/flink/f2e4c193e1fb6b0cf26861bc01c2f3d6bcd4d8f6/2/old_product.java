@SuppressWarnings("unchecked")
	public List<Tuple2<StateHandle<T>, String>> getAllSortedByName() throws Exception {
		final List<Tuple2<StateHandle<T>, String>> stateHandles = new ArrayList<>();

		boolean success = false;

		retry:
		while (!success) {
			Stat stat = client.checkExists().forPath("/");
			if (stat == null) {
				break; // Node does not exist, done.
			} else {
				// Initial cVersion (number of changes to the children of this node)
				int initialCVersion = stat.getCversion();

				List<String> children = ZKPaths.getSortedChildren(
						client.getZookeeperClient().getZooKeeper(),
						ZKPaths.fixForNamespace(client.getNamespace(), "/"));

				for (String path : children) {
					path = "/" + path;

					try {
						final StateHandle<T> stateHandle = get(path);
						stateHandles.add(new Tuple2<>(stateHandle, path));
					} catch (KeeperException.NoNodeException ignored) {
						// Concurrent deletion, retry
						continue retry;
					}
				}

				int finalCVersion = client.checkExists().forPath("/").getCversion();

				// Check for concurrent modifications
				success = initialCVersion == finalCVersion;
			}
		}

		return stateHandles;
	}