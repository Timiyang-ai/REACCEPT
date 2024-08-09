public boolean process( T image ) {
		tracker.process(image);

		tick++;
		inlierTracks.clear();

		if( first ) {
			addNewTracks();
			first = false;
		} else {
			if( !estimateMotion() ) {
				return false;
			}

			dropUnusedTracks();
			int N = motionEstimator.getMatchSet().size();

			if( thresholdAdd <= 0 || N < thresholdAdd ) {
				changePoseToReference();
				addNewTracks();
			}

//			System.out.println("  num inliers = "+N+"  num dropped "+numDropped+" total active "+tracker.getActivePairs().size());
		}

		return true;
	}