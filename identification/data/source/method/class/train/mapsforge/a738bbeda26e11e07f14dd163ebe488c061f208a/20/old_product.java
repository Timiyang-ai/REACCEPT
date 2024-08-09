public static double pixelXToLongitude(double pixelX, byte zoomLevel) {
		long mapSize = getMapSize(zoomLevel);
		if (pixelX < 0 || pixelX > mapSize) {
			throw new IllegalArgumentException("invalid pixelX coordinate at zoom level " + zoomLevel + ": " + pixelX);
		}
		return 360 * ((pixelX / mapSize) - 0.5);
	}