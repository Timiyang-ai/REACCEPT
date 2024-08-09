@Deprecated
	public static Point LatLongToPixelXY(
			final double latitude, final double longitude, final int levelOfDetail, final Point reuse) {
		final Point out = (reuse == null ? new Point() : reuse);
		final int size = MapSize(levelOfDetail);
		out.x = truncateToInt(getMercatorXFromLongitude(longitude, size, true));
		out.y = truncateToInt(getMercatorYFromLatitude(latitude, size, true));
		return out;
	}