public static double getLongitudeFromX01(final double pX01, boolean wrapEnabled) {
		return MinLongitude + (MaxLongitude - MinLongitude) * (wrapEnabled ? Clip(pX01, 0, 1) : pX01);
	}