public void changeKeyFrame() {
		tracker.dropAllTracks();
		tracker.spawnTracks();

		List<PointTrack> spawned = tracker.getNewTracks(null);

		for( PointTrack l : spawned ) {
			AssociatedPairTrack p = l.getCookie();
			if( p == null ) {
				l.cookie = p = new AssociatedPairTrack();
				// little bit of trickery here.  Save the reference so that the point
				// in the current frame is updated for free as PointTrack is
				p.p2 = l;
				l.cookie = p;
			}
			p.p1.set(l);
			p.lastUsed = totalFramesProcessed;
		}

		totalSpawned = spawned.size();
		worldToKey.set(worldToCurr);
	}