public double getLongitudeFromX01(final double pX01, boolean wrapEnabled) {
        final double longitude = getLongitudeFromX01(wrapEnabled ? Clip(pX01, 0, 1) : pX01);
        return wrapEnabled ? Clip(longitude, getMinLongitude(), getMaxLongitude()) : longitude;
	}