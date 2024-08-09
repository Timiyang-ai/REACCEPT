public void changeKeyFrame() {
		tracker.dropAllTracks();
		tracker.spawnTracks();

		spawned = tracker.getNewTracks(null);

		totalSpawned = spawned.size();
		worldToKey.set(worldToCurr);
		keyFrame = true;
	}