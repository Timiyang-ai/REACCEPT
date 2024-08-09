public void changeKeyFrame() {
		tracker.setCurrentToKeyFrame();
		tracker.spawnTracks();

		totalSpawned = tracker.getActiveTracks().size();
		worldToKey.set(worldToCurr);
	}