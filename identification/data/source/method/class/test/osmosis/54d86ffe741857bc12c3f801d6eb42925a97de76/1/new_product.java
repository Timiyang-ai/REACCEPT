public void process(Bound bound) {
		if (legacyBound) {
			processLegacy(bound);
		} else {
			processRegular(bound);
		}
	}