public boolean process( I frame ) {
		keyFrame = false;

		// update the feature tracker
		tracker.process(frame);

		totalFramesProcessed++;

		List<PointTrack> tracks = tracker.getActiveTracks(null);

		if( tracks.size() == 0 )
			return false;

		List<AssociatedPair> pairs = new ArrayList<AssociatedPair>();
		for( PointTrack t : tracks ) {
			pairs.add((AssociatedPair)t.getCookie());
		}

		// fit the motion model to the feature tracks
		if( !modelMatcher.process((List)pairs) ) {
			return false;
		}

		if( modelRefiner != null ) {
			if( !modelRefiner.fitModel(modelMatcher.getMatchSet(),modelMatcher.getModel(),keyToCurr) )
				return false;
		} else {
			keyToCurr.set(modelMatcher.getModel());
		}

		// mark that the track is in the inlier set
		for( AssociatedPair p : modelMatcher.getMatchSet() ) {
			((AssociatedPairTrack)p).lastUsed = totalFramesProcessed;
		}

		// prune tracks which aren't being used
		pruneUnusedTracks();

		// Update the motion
		worldToKey.concat(keyToCurr, worldToCurr);

		return true;
	}