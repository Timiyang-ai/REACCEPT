public int distanceTo(final IGeoPoint other) {

		final double a1 = DEG2RAD * this.mLatitude;
		final double a2 = DEG2RAD * this.mLongitude;
		final double b1 = DEG2RAD * other.getLatitude();
		final double b2 = DEG2RAD * other.getLongitude();

		final double cosa1 = Math.cos(a1);
		final double cosb1 = Math.cos(b1);

		final double t1 = cosa1 * Math.cos(a2) * cosb1 * Math.cos(b2);

		final double t2 = cosa1 * Math.sin(a2) * cosb1 * Math.sin(b2);

		final double t3 = Math.sin(a1) * Math.sin(b1);

		final double tt = Math.acos(t1 + t2 + t3);

		return (int) (RADIUS_EARTH_METERS * tt);
	}