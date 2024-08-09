public static long pixelYToTileY(double pixelY, byte zoomLevel, int tileSize) {
		return (long) Math.min(Math.max(pixelY / tileSize, 0), Math.pow(2, zoomLevel) - 1);
	}