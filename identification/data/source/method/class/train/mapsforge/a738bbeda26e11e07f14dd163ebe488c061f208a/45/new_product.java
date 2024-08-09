public static double tileYToLatitude(long tileY, byte zoomLevel) {
		return pixelYToLatitude(tileY * DUMMY_TILE_SIZE, zoomLevel, DUMMY_TILE_SIZE);
	}