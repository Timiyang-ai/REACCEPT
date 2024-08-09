public boolean process( T leftImage ) {
		tracker.process(leftImage);

		inliersValid = false;

		if( !hasSignificantChange ) {
			if( !checkSignificantMotion() ) {
				return false;
			} else
				hasSignificantChange = true;
		}

		if( first ) {
			setNewKeyFrame();
			first = false;
		} else {
			if( !estimateMotion() ) {
				motionFailed++;
				return false;
			}

			System.out.println(" numTracksUsed = "+numTracksUsed+"  original "+numOriginalUsed+" total "+tracker.getPairs().size());
			if( numOriginalUsed < MIN_TRACKS/2 ) {
				setNewKeyFrame();
				inliersValid = false;
			} else if( numTracksUsed < MIN_TRACKS ) {
				addNewTracks();
			}
		}

		return true;
	}