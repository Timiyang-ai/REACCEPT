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

		// refine the motion estimate
		if( modelRefiner == null ||
				!modelRefiner.fitModel(modelMatcher.getMatchSet(),modelMatcher.getModel(),keyToCurr))
		{
			keyToCurr.set(modelMatcher.getModel());
		}

		// Update the motion
		worldToKey.concat(keyToCurr, worldToCurr);

		return true;
	}