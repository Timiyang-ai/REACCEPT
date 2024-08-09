public StateHandle<T> add(
			String pathInZooKeeper,
			T state,
			CreateMode createMode) throws Exception {
		checkNotNull(pathInZooKeeper, "Path in ZooKeeper");
		checkNotNull(state, "State");

		StateHandle<T> stateHandle = storage.store(state);

		boolean success = false;

		try {
			// Serialize the state handle. This writes the state to the backend.
			byte[] serializedStateHandle = InstantiationUtil.serializeObject(stateHandle);

			// Write state handle (not the actual state) to ZooKeeper. This is expected to be
			// smaller than the state itself. This level of indirection makes sure that data in
			// ZooKeeper is small, because ZooKeeper is designed for data in the KB range, but
			// the state can be larger.
			client.create().withMode(createMode).forPath(pathInZooKeeper, serializedStateHandle);

			success = true;

			return stateHandle;
		}
		finally {
			if (!success) {
				// Cleanup the state handle if it was not written to ZooKeeper.
				if (stateHandle != null) {
					stateHandle.discardState();
				}
			}
		}
	}