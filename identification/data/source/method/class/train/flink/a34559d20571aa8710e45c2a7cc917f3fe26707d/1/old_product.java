@Override
	public void recover() throws Exception {
		LOG.info("Recovering checkpoints from ZooKeeper.");

		// Clear local handles in order to prevent duplicates on
		// recovery. The local handles should reflect the state
		// of ZooKeeper.
		checkpointStateHandles.clear();

		// Get all there is first
		List<Tuple2<StateHandle<CompletedCheckpoint>, String>> initialCheckpoints;
		while (true) {
			try {
				initialCheckpoints = checkpointsInZooKeeper.getAllSortedByName();
				break;
			}
			catch (ConcurrentModificationException e) {
				LOG.warn("Concurrent modification while reading from ZooKeeper. Retrying.");
			}
		}

		int numberOfInitialCheckpoints = initialCheckpoints.size();

		LOG.info("Found {} checkpoints in ZooKeeper.", numberOfInitialCheckpoints);

		if (numberOfInitialCheckpoints > 0) {
			// Take the last one. This is the latest checkpoints, because path names are strictly
			// increasing (checkpoint ID).
			Tuple2<StateHandle<CompletedCheckpoint>, String> latest = initialCheckpoints
					.get(numberOfInitialCheckpoints - 1);

			CompletedCheckpoint latestCheckpoint;
			long checkpointId = pathToCheckpointId(latest.f1);

			LOG.info("Trying to retrieve checkpoint {}.", checkpointId);

			try {
				latestCheckpoint = latest.f0.getState(userClassLoader);
			} catch (Exception e) {
				throw new Exception("Could not retrieve the completed checkpoint " + checkpointId +
				" from the state storage.", e);
			}

			checkpointStateHandles.add(latest);

			LOG.info("Initialized with {}. Removing all older checkpoints.", latestCheckpoint);

			for (int i = 0; i < numberOfInitialCheckpoints - 1; i++) {
				try {
					removeFromZooKeeperAndDiscardCheckpoint(initialCheckpoints.get(i));
				}
				catch (Exception e) {
					LOG.error("Failed to discard checkpoint", e);
				}
			}
		}
	}