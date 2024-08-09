@Override
	public List<Point> geoPos(byte[] key, byte[]... members) {

		List<Point> result = delegate.geoPos(key, members);
		if (isFutureConversion()) {
			addResultConverter(identityConverter);
		}
		return result;
	}