public static int latitudeToTileY(double latitude, byte zoomLevel) {
		return pixelYToTileY(latitudeToPixelY(latitude, zoomLevel, DUMMY_TILE_SIZE), zoomLevel, DUMMY_TILE_SIZE);
	}