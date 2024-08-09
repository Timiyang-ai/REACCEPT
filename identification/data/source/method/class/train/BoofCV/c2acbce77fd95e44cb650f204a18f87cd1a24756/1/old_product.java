public void changeKeyFrame() {
		tracker.dropAllTracks();
		tracker.spawnTracks();

		spawned = tracker.getNewTracks(null);
		for( PointTrack t : spawned )
			handleSpawnedTrack(t);

		totalSpawned = spawned.size();
		worldToKey.set(worldToCurr);
		keyFrame = true;
	}