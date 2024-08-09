public boolean process( I frame ) {
		// update the feature tracker
		tracker.process(frame);
		totalProcessed++;

		// set up data structures and spawn tracks
		if( totalProcessed == 1 ) {
			tracker.spawnTracks();
			worldToKey.set(worldToInit);
			worldToCurr.set(worldToInit);
			return true;
		}

		// fit the motion model to the feature tracks
		List<AssociatedPair> pairs = tracker.getActiveTracks();
		if( !modelMatcher.process(pairs,null) ) {
			return false;
		}

		// Update the motion
		keyToCurr.set(modelMatcher.getModel());
		worldToKey.concat(keyToCurr, worldToCurr);

		return true;
	}