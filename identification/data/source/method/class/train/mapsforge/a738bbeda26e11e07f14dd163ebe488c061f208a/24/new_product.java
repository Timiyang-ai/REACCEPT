public static double longitudeToPixelX(double longitude, byte zoomLevel, int tileSize) {
		long mapSize = getMapSize(zoomLevel, tileSize);
		return (longitude + 180) / 360 * mapSize;
	}