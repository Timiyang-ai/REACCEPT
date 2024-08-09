public static long pixelYToTileY(double pixelY, byte zoomLevel) {
		return (long) Math.min(Math.max(pixelY / GraphicFactory.getTileSize(), 0), Math.pow(2, zoomLevel) - 1);
	}