public static long pixelXToTileX(double pixelX, byte zoomLevel) {
		return (long) Math.min(Math.max(pixelX / Tile.TILE_SIZE, 0), Math.pow(2, zoomLevel) - 1);
	}