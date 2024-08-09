@Deprecated
	public static GeoPoint PixelXYToLatLong(final int pixelX, final int pixelY,
			final int levelOfDetail, final GeoPoint reuse) {
		return org.osmdroid.util.TileSystem.PixelXYToLatLong(pixelX, pixelY, levelOfDetail, reuse);
	}