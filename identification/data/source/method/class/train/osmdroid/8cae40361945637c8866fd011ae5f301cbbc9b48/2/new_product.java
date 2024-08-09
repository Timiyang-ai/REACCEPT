public double getRandomLongitude(final double pRandom01) {
		return pRandom01 * (getMaxLongitude() - getMinLongitude()) + getMinLongitude();
	}