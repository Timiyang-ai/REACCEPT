public double getLatitudeFromY01(final double pY01, boolean wrapEnabled) {
		final double latitude = getLatitudeFromY01(wrapEnabled ? Clip(pY01, 0, 1) : pY01);
		return wrapEnabled ? Clip(latitude, getMinLatitude(), getMaxLatitude()) : latitude;
	}