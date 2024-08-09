public static long pixelXToTileX(double pixelX, byte zoomLevel, int tileSize) {
		return (long) Math.min(Math.max(pixelX / tileSize, 0), Math.pow(2, zoomLevel) - 1);
	}