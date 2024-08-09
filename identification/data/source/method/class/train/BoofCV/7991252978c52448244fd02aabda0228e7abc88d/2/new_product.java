public boolean process( I frame ) {
		// update the feature tracker
		tracker.process(frame);
		totalProcessed++;

		// set up data structures and spawn tracks
		if( totalProcessed == 1 ) {
			tracker.spawnTracks();
			tracker.setKeyFrame();

			worldToKey.set(worldToInit);
			worldToCurr.set(worldToInit);
			return true;
		}

		// fit the motion model to the feature tracks
		List<AssociatedPair> pairs = tracker.getPairs();
		if( !modelMatcher.process(pairs,null) ) {
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