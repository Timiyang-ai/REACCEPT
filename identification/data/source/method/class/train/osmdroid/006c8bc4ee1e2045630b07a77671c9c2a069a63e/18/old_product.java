public static Point LatLongToPixelXY(
			final double latitude, final double longitude, final double zoomLevel, final Point reuse) {
		return LatLongToPixelXYMapSize(
				wrap(latitude, -90, 90, 180),
				wrap(longitude, -180, 180, 360),
				MapSize(zoomLevel), reuse);
	}