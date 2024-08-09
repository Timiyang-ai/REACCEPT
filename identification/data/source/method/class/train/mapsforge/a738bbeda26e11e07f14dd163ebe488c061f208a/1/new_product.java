public static double tileXToLongitude(long tileX, double scaleFactor) {
		return pixelXToLongitudeWithScaleFactor(tileX * DUMMY_TILE_SIZE, scaleFactor, DUMMY_TILE_SIZE);
	}