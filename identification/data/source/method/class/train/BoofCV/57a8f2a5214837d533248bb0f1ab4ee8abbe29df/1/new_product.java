@SuppressWarnings({"SuspiciousNameCombination"})
	public boolean setDescription(KltFeature feature) {
		if (derivX == null || derivY == null)
			throw new IllegalArgumentException("Image derivatives must be set");

		setAllowedBounds(feature);

		if (!isFullyInside(feature.x, feature.y)) {
			return false;
		}

		internalSetDescription(feature);

		return true;
	}