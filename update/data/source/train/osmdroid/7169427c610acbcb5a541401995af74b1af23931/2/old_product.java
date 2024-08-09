public static double getLatitudeFromY01(final double pY01, boolean wrapEnabled) {
		double latitude = 90 - 360 * Math.atan(Math.exp((pY01 - 0.5) * 2 * Math.PI)) / Math.PI;
		return wrapEnabled ? Clip(latitude, MinLatitude, MaxLatitude) : latitude;
	}