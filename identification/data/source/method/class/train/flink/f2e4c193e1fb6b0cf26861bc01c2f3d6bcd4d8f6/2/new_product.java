public void replace(String pathInZooKeeper, int expectedVersion, T state) throws Exception {
		checkNotNull(pathInZooKeeper, "Path in ZooKeeper");
		checkNotNull(state, "State");

		RetrievableStateHandle<T> oldStateHandle = get(pathInZooKeeper);

		RetrievableStateHandle<T> newStateHandle = storage.store(state);

		boolean success = false;

		try {
			// Serialize the new state handle. This writes the state to the backend.
			byte[] serializedStateHandle = InstantiationUtil.serializeObject(newStateHandle);

			// Replace state handle in ZooKeeper.
			client.setData()
					.withVersion(expectedVersion)
					.forPath(pathInZooKeeper, serializedStateHandle);
			success = true;
		} finally {
			if(success) {
				oldStateHandle.discardState();
			} else {
				newStateHandle.discardState();
			}
		}

	}