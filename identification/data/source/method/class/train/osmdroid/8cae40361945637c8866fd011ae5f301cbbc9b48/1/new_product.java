public double getRandomLatitude(final double pRandom01, final double pMinLatitude) {
		return pRandom01 * (getMaxLatitude() - pMinLatitude) + pMinLatitude;
	}