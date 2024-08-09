@Override
	public boolean dropTrack(PointTrack track) {
		if( !tracksAll.remove(track) )
			return false;
		// the track may or may not be in the active list
		tracksActive.remove(track);
		tracksInactive.remove(track);
		// it must be in the all list
		// recycle the data
		unused.add(track);
		return true;
	}