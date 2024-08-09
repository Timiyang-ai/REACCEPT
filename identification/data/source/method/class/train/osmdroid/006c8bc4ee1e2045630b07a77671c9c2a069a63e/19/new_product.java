@Deprecated
	public static GeoPoint PixelXYToLatLong(
			final int pixelX, final int pixelY, final int levelOfDetail, final GeoPoint reuse) {
		final int mapSize = MapSize(levelOfDetail);
		return microsoft.mappoint.TileSystem.PixelXYToLatLong(
				(int) wrap(pixelX, 0, mapSize - 1, mapSize),
				(int) wrap(pixelY, 0, mapSize - 1, mapSize),
				levelOfDetail, reuse);
	}