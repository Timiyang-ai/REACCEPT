public static double longitudeToPixelXWithScaleFactor(double longitude, double scaleFactor, int tileSize) {
		long mapSize = getMapSizeWithScaleFactor(scaleFactor, tileSize);
		return (longitude + 180) / 360 * mapSize;
	}