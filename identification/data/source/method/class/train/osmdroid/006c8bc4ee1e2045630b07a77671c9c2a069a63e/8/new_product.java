public static Point LatLongToPixelXY(double latitude, double longitude,
			final int levelOfDetail, final Point reuse) {
		final Point out = (reuse == null ? new Point() : reuse);

		latitude = Clip(latitude, MinLatitude, MaxLatitude);
		longitude = Clip(longitude, MinLongitude, MaxLongitude);

		final double x = (longitude + 180) / 360;
		final double sinLatitude = Math.sin(latitude * Math.PI / 180);
		final double y = 0.5 - Math.log((1 + sinLatitude) / (1 - sinLatitude)) / (4 * Math.PI);

		final int mapSize = MapSize(levelOfDetail);
		out.x = (int) Clip(x * mapSize + 0.5, 0, mapSize - 1);
		out.y = (int) Clip(y * mapSize + 0.5, 0, mapSize - 1);
		return out;
	}