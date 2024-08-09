public static GeoPoint projectGeoPoint(final double aLat, final double aLon, final int aZoom,
			final GeoPoint aUseAsReturnValue) {
		final GeoPoint out = aUseAsReturnValue != null ? aUseAsReturnValue : new GeoPoint(0, 0);

		out.setLongitudeE6((int) Math.floor((aLon + 180) / 360 * (1 << aZoom)));
		out.setLatitudeE6((int) Math.floor((1 - Math.log(Math.tan(aLat * DEG2RAD) + 1
				/ Math.cos(aLat * DEG2RAD))
				/ Math.PI)
				/ 2 * (1 << aZoom)));

		return out;
	}