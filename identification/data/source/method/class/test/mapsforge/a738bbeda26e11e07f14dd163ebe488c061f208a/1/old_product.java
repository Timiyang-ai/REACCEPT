public static double pixelYToLatitude(double pixelY, double scaleFactor, int tileSize) {
		long mapSize = getMapSize(scaleFactor, tileSize);
		if (pixelY < 0 || pixelY > mapSize) {
			throw new IllegalArgumentException("invalid pixelY coordinate at zoom level " + scaleFactor + ": " + pixelY);
		}
		double y = 0.5 - (pixelY / mapSize);
		return 90 - 360 * Math.atan(Math.exp(-y * (2 * Math.PI))) / Math.PI;
	}