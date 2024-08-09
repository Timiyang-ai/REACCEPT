public static long pixelYToTileY(double pixelY, byte zoomLevel) {
		return (long) Math.min(Math.max(pixelY / Tile.TILE_SIZE, 0), Math.pow(2, zoomLevel) - 1);
	}