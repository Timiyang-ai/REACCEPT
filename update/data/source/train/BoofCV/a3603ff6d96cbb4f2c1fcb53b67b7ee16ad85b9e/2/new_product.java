public boolean process( I frame ) {
		keyFrame = false;

		// update the feature tracker
		tracker.process(frame);

		totalFramesProcessed++;

		if( !tracker.foundModel() ) {
			return false;
		}

		keyToCurr.set(tracker.getModel());

		// mark that the track is in the inlier set
		for( AssociatedPair p : tracker.getMatchSet() ) {
			((AssociatedPairTrack)p).lastUsed = totalFramesProcessed;
		}

		// Update the motion
		worldToKey.concat(keyToCurr, worldToCurr);

		// prune tracks which aren't being used
		List<PointTrack> all = tracker.getAllTracks(null);
		for( PointTrack t : all ) {
			AssociatedPairTrack p = t.getCookie();

			if( totalFramesProcessed - p.lastUsed >= pruneThreshold ) {
				tracker.dropTrack(t);
			}
		}

		return true;
	}