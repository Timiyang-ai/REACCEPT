public double bearingTo(final IGeoPoint other) {
		final double lat1 = Math.toRadians(this.mLatitude);
		final double long1 = Math.toRadians(this.mLongitude);
		final double lat2 = Math.toRadians(other.getLatitude());
		final double long2 = Math.toRadians(other.getLongitude());
		final double delta_long = long2 - long1;
		final double a = Math.sin(delta_long) * Math.cos(lat2);
		final double b = Math.cos(lat1) * Math.sin(lat2) -
						 Math.sin(lat1) * Math.cos(lat2) * Math.cos(delta_long);
		final double bearing = Math.toDegrees(Math.atan2(a, b));
		final double bearing_normalized = (bearing + 360) % 360;
		return bearing_normalized;
	}