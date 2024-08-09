public static long pixelYToTileY(double pixelY, double scaleFactor, int tileSize) {
		return (long) Math.min(Math.max(pixelY / tileSize, 0), scaleFactor - 1);
	}