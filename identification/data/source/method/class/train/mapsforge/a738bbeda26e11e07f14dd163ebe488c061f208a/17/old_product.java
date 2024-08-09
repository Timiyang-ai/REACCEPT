public static long getMapSize(byte zoomLevel) {
		if (zoomLevel < 0) {
			throw new IllegalArgumentException("zoom level must not be negative: " + zoomLevel);
		}
		return (long) GraphicFactory.getTileSize() << zoomLevel;
	}