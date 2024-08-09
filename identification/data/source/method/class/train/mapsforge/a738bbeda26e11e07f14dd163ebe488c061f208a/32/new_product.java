public static double tileXToLongitude(long tileX, byte zoomLevel) {
		return pixelXToLongitude(tileX * dummyTileSize, zoomLevel, dummyTileSize);
	}