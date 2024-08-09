public static int pixelYToTileY(double pixelY, byte zoomLevel, int tileSize) {
		return (int) Math.min(Math.max(pixelY / tileSize, 0), Math.pow(2, zoomLevel) - 1);
	}