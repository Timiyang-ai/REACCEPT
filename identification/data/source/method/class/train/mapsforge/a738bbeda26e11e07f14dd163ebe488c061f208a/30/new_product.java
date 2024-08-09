public static double tileYToLatitude(long tileY, byte zoomLevel) {
		return pixelYToLatitude(tileY * DUMMY_TILE_SIZE, getMapSize(zoomLevel, DUMMY_TILE_SIZE));
	}