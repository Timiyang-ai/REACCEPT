@Deprecated
	public static GeoPoint PixelXYToLatLong(
			final int pixelX, final int pixelY, final double zoomLevel, final GeoPoint reuse) {
		return getGeoFromMercator(pixelX, pixelY, MapSize(zoomLevel), reuse, true, true);
	}