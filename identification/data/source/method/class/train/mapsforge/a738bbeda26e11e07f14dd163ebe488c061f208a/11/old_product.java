public static long getMapSize(double scaleFactor, int tileSize) {
		if (scaleFactor < 1) {
			throw new IllegalArgumentException("scale factor must not < 1 " + scaleFactor);
		}
		return (long) (tileSize * (Math.pow(2, scaleFactorToZoomLevel(scaleFactor))));
	}