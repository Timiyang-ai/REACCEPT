public static double pixelXToLongitudeWithScaleFactor(double pixelX, double scaleFactor, int tileSize) {
		long mapSize = getMapSizeWithScaleFactor(scaleFactor, tileSize);
		if (pixelX < 0 || pixelX > mapSize) {
			throw new IllegalArgumentException("invalid pixelX coordinate at zoom level " + scaleFactor + ": " + pixelX);
		}
		return 360 * ((pixelX / mapSize) - 0.5);
	}