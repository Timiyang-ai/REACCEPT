public static GeoPoint PixelXYToLatLong(
			final int pixelX, final int pixelY, final double zoomLevel, final GeoPoint reuse) {
		final double mapSize = MapSize(zoomLevel);
		return PixelXYToLatLongMapSize(
				(int) wrap(pixelX, 0, mapSize - 1, mapSize),
				(int) wrap(pixelY, 0, mapSize - 1, mapSize),
				mapSize, reuse);
	}