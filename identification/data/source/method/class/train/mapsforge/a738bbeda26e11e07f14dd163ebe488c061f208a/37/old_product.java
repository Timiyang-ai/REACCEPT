public static double tileYToLatitude(long tileY, double scaleFactor) {
		return pixelYToLatitude(tileY * DUMMY_TILE_SIZE, scaleFactor, DUMMY_TILE_SIZE);
	}