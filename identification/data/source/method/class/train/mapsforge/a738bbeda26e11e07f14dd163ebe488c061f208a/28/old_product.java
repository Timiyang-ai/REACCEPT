public static double pixelYToLatitude(double pixelY, byte zoomLevel) {
		long mapSize = getMapSize(zoomLevel);
		if (pixelY < 0 || pixelY > mapSize) {
			throw new IllegalArgumentException("invalid pixelY coordinate at zoom level " + zoomLevel + ": " + pixelY);
		}
		double y = 0.5 - (pixelY / mapSize);
		return 90 - 360 * Math.atan(Math.exp(-y * (2 * Math.PI))) / Math.PI;
	}