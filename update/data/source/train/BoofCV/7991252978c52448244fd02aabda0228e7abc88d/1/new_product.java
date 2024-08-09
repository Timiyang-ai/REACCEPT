public void changeKeyFrame() {
		tracker.spawnTracks();
		tracker.setKeyFrame();

		totalSpawned = tracker.getActiveTracks().size();
		worldToKey.set(worldToCurr);
	}