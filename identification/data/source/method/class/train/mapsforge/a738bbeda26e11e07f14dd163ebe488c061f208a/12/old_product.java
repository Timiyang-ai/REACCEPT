public static double tileYToLatitude(long tileY, byte zoomLevel) {
		return pixelYToLatitude(tileY * Tile.TILE_SIZE, zoomLevel);
	}