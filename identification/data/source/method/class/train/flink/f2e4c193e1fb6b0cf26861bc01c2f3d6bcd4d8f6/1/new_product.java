@SuppressWarnings("unchecked")
	public List<Tuple2<StateHandle<T>, String>> getAll() throws Exception {
		final List<Tuple2<StateHandle<T>, String>> stateHandles = new ArrayList<>();

		boolean success = false;

		retry:
		while (!success) {
			stateHandles.clear();

			Stat stat = client.checkExists().forPath("/");
			if (stat == null) {
				break; // Node does not exist, done.
			} else {
				// Initial cVersion (number of changes to the children of this node)
				int initialCVersion = stat.getCversion();

				List<String> children = client.getChildren().forPath("/");

				for (String path : children) {
					path = "/" + path;

					try {
						final StateHandle<T> stateHandle = get(path);
						stateHandles.add(new Tuple2<>(stateHandle, path));
					} catch (KeeperException.NoNodeException ignored) {
						// Concurrent deletion, retry
						continue retry;
					} catch (IOException ioException) {
						LOG.warn("Could not get all ZooKeeper children. Node {} contained " +
							"corrupted data. Ignoring this node.", path, ioException);
					}
				}

				int finalCVersion = client.checkExists().forPath("/").getCversion();

				// Check for concurrent modifications
				success = initialCVersion == finalCVersion;
			}
		}

		return stateHandles;
	}