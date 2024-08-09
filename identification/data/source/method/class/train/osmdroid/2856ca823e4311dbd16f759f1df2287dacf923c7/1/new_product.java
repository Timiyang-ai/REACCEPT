public GeoPoint destinationPoint(final double aDistanceInMeters, final double aBearingInDegrees) {

		// convert distance to angular distance
		final double dist = aDistanceInMeters / RADIUS_EARTH_METERS;

		// convert bearing to radians
		final double brng = DEG2RAD * aBearingInDegrees;

		// get current location in radians
		final double lat1 = DEG2RAD * getLatitude();
		final double lon1 = DEG2RAD * getLongitude();

		final double lat2 = Math.asin(Math.sin(lat1) * Math.cos(dist) + Math.cos(lat1)
				* Math.sin(dist) * Math.cos(brng));
		final double lon2 = lon1
		+ Math.atan2(Math.sin(brng) * Math.sin(dist) * Math.cos(lat1), Math.cos(dist)
				- Math.sin(lat1) * Math.sin(lat2));

		final double lat2deg = lat2 / DEG2RAD;
		final double lon2deg = lon2 / DEG2RAD;

		return new GeoPoint(lat2deg, lon2deg);
	}