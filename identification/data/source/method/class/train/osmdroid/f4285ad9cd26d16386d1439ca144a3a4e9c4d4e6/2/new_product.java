public static long MapSize(final int levelOfDetail) {
		return ((long) mTileSize) << levelOfDetail;
	}