public static double tileYToLatitude(long tileY, byte zoomLevel) {
		return pixelYToLatitude(tileY * dummyTileSize, zoomLevel, dummyTileSize);
	}