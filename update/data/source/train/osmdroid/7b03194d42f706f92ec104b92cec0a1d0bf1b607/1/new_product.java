public static GeoPoint projectGeoPoint(final int aLatE6, final int aLonE6, final int aZoom,
			final GeoPoint reuse) {
		return projectGeoPoint(aLatE6 * 1E-6, aLonE6 * 1E-6, aZoom, reuse);
	}