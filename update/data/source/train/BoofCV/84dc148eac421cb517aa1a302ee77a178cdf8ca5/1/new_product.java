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

		keyToCurr.set(modelMatcher.getModel());

		// mark that the track is in the inlier set and compute the containment rectangle
		contRect.x0 = contRect.y0 = Double.MAX_VALUE;
		contRect.x1 = contRect.y1 = -Double.MAX_VALUE;
		for( AssociatedPair p : modelMatcher.getMatchSet() ) {
			((AssociatedPairTrack)p).lastUsed = totalFramesProcessed;

			Point2D_F64 t = p.p2;
			if( t.x > contRect.x1 )
				contRect.x1 = t.x;
			if( t.y > contRect.y1 )
				contRect.y1 = t.y;
			if( t.x < contRect.x0 )
				contRect.x0 = t.x;
			if( t.y < contRect.y0 )
				contRect.y0 = t.y;
		}
		contFraction = contRect.area()/(frame.width*frame.height);

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