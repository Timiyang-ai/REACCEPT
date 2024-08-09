@Deprecated
	public static GeoPoint PixelXYToLatLong(
			final int pixelX, final int pixelY, final int levelOfDetail, final GeoPoint reuse) {
		return getGeoFromMercator(pixelX, pixelY, MapSize(levelOfDetail), reuse, true, true);
	}