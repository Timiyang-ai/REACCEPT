public static int longitudeToTileX(double longitude, double scaleFactor) {
		return pixelXToTileX(longitudeToPixelXWithScaleFactor(longitude, scaleFactor, DUMMY_TILE_SIZE), scaleFactor, DUMMY_TILE_SIZE);
	}