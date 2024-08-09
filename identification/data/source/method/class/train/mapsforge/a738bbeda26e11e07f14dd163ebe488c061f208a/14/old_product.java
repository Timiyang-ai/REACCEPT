public static long pixelXToTileX(double pixelX, double scaleFactor, int tileSize) {
		return (long) Math.min(Math.max(pixelX / tileSize, 0), scaleFactor - 1);
	}