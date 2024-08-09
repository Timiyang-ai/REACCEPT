public static GeoPoint PixelXYToLatLong(int pixelX, int pixelY, int levelOfDetail,
			GeoPoint reuse) {
		GeoPoint out = (reuse == null ? new GeoPoint(0, 0) : reuse);

		double mapSize = MapSize(levelOfDetail);
		double x = (Clip(pixelX, 0, mapSize - 1) / mapSize) - 0.5;
		double y = 0.5 - (Clip(pixelY, 0, mapSize - 1) / mapSize);

		double latitude = 90 - 360 * Math.atan(Math.exp(-y * 2 * Math.PI)) / Math.PI;
		double longitude = 360 * x;

		out.setLatitudeE6((int) (latitude * 1E6));
		out.setLongitudeE6((int) (longitude * 1E6));
		return out;
	}