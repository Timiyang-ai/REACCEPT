public void process( I image ) {
		tracker.process(image);

		activePairs.clear();
		List<PointTrack> tracks = tracker.getActiveTracks();
		for( PointTrack t : tracks ) {
			R p = t.getCookie();
			p.pixel.p2.set(t);
			pixelToNorm.compute(t.x, t.y, p.p2);
			activePairs.add(p);
		}
	}