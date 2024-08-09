public static long getMapSize(byte zoomLevel, int tileSize) {
		if (zoomLevel < 0) {
			throw new IllegalArgumentException("zoom level must not be negative: " + zoomLevel);
		}
		return (long) tileSize << zoomLevel;
	}