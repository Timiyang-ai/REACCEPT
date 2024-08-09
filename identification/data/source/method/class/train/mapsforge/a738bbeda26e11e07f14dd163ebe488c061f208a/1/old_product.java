public static double tileXToLongitude(long tileX, double scaleFactor) {
		return pixelXToLongitude(tileX * DUMMY_TILE_SIZE, scaleFactor, DUMMY_TILE_SIZE);
	}