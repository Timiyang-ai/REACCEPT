public static int longitudeToTileX(double longitude, byte zoomLevel) {
		return pixelXToTileX(longitudeToPixelX(longitude, getMapSize(zoomLevel, DUMMY_TILE_SIZE)), zoomLevel, DUMMY_TILE_SIZE);
	}