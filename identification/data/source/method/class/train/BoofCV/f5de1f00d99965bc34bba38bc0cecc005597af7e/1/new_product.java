public boolean process( I frame ) {
		// update the feature tracker
		tracker.process(frame);
		totalProcessed++;

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

		// mark that the track is in the inlier set
		for( AssociatedPair p : modelMatcher.getMatchSet() ) {
			((AssociatedPairTrack)p).lastUsed = totalProcessed;
		}

		// refine the motion estimate
		if( modelRefiner == null ||
				!modelRefiner.fitModel(modelMatcher.getMatchSet(),modelMatcher.getModel(),keyToCurr))
		{
			keyToCurr.set(modelMatcher.getModel());
		}

		// Update the motion
		worldToKey.concat(keyToCurr, worldToCurr);

		// prune tracks which aren't being used
		List<PointTrack> all = tracker.getAllTracks(null);
		for( PointTrack t : all ) {
			AssociatedPairTrack p = t.getCookie();

			if( totalProcessed - p.lastUsed >= pruneThreshold ) {
				System.out.println("Delta "+(totalProcessed-p.lastUsed));
				tracker.dropTrack(t);
			}
		}

		return true;
	}