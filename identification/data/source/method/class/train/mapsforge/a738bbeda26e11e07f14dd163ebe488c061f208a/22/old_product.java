public static long longitudeToTileX(double longitude, byte zoomLevel) {
		return pixelXToTileX(longitudeToPixelX(longitude, zoomLevel), zoomLevel);
	}