public static long latitudeToTileY(double latitude, double scaleFactor) {
		return pixelYToTileY(latitudeToPixelY(latitude, scaleFactor, DUMMY_TILE_SIZE), scaleFactor, DUMMY_TILE_SIZE);
	}