public static double tileXToLongitude(long tileX, byte zoomLevel) {
		return pixelXToLongitude(tileX * DUMMY_TILE_SIZE, getMapSize(zoomLevel, DUMMY_TILE_SIZE));
	}