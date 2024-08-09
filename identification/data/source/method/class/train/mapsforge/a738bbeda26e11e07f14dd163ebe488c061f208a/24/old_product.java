public static double longitudeToPixelX(double longitude, byte zoomLevel) {
		long mapSize = getMapSize(zoomLevel);
		return (longitude + 180) / 360 * mapSize;
	}