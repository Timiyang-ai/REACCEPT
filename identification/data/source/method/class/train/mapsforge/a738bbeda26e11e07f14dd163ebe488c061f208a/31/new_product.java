public static long longitudeToTileX(double longitude, byte zoomLevel) {
		return pixelXToTileX(longitudeToPixelX(longitude, zoomLevel, DUMMY_TILE_SIZE), zoomLevel, DUMMY_TILE_SIZE);
	}