public static long getMaxTileNumber(byte zoomLevel) {
		if (zoomLevel < 0) {
			throw new IllegalArgumentException("zoomLevel must not be negative: " + zoomLevel);
		} else if (zoomLevel == 0) {
			return 0;
		}
		return (2 << zoomLevel - 1) - 1;
	}