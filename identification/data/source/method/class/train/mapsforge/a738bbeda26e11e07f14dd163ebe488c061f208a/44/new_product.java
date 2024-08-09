public static int longitudeToTileX(double longitude, double scaleFactor) {
		return pixelXToTileX(longitudeToPixelX(longitude, scaleFactor, DUMMY_TILE_SIZE), scaleFactor, DUMMY_TILE_SIZE);
	}