public void changeKeyFrame() {
		tracker.dropAllTracks();
		tracker.spawnTracks();

		List<PointTrack> spawned = tracker.getNewTracks(null);

		for( PointTrack l : spawned ) {
			AssociatedPair p = l.getCookie();
			if( p == null ) {
				l.cookie = p = new AssociatedPair();
				// little bit of trickery here.  Save the reference so that the point
				// in the current frame is updated for free as PointTrack is
				p.p2 = l;
				l.cookie = p;
			}
			p.p1.set(p.p2);
		}

		totalSpawned = spawned.size();
		worldToKey.set(worldToCurr);
	}