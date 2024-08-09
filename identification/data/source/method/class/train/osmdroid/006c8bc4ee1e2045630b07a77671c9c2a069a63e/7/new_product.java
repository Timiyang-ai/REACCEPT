@Deprecated
	public static Point LatLongToPixelXY(double latitude, double longitude,
			final int levelOfDetail, final Point reuse) {
		return org.osmdroid.util.TileSystem.LatLongToPixelXY(latitude, longitude, levelOfDetail, reuse);
	}