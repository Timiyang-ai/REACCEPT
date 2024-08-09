public static long latitudeToTileY(double latitude, byte zoomLevel) {
		return pixelYToTileY(latitudeToPixelY(latitude, zoomLevel), zoomLevel);
	}