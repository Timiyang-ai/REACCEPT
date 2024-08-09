public static GeoPoint PixelXYToLatLong(final int pixelX, final int pixelY,
			final int levelOfDetail, final GeoPoint reuse) {
		final GeoPoint out = (reuse == null ? new GeoPoint(0, 0) : reuse);

		final double mapSize = MapSize(levelOfDetail);
		final double x = (Clip(pixelX, 0, mapSize - 1) / mapSize) - 0.5;
		final double y = 0.5 - (Clip(pixelY, 0, mapSize - 1) / mapSize);

		final double latitude = 90 - 360 * Math.atan(Math.exp(-y * 2 * Math.PI)) / Math.PI;
		final double longitude = 360 * x;

		out.setLatitudeE6((int) (latitude * 1E6));
		out.setLongitudeE6((int) (longitude * 1E6));
		return out;
	}