@Override
	public Long geoRemove(byte[] key, byte[]... values) {
		return zRem(key, values);
	}