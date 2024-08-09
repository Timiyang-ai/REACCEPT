@Override
	public List<String> geoHash(byte[] key, byte[]... members) {

		List<String> result = delegate.geoHash(key, members);
		if (isFutureConversion()) {
			addResultConverter(identityConverter);
		}
		return result;
	}