@Override
	public Long geoRemove(byte[] key, byte[]... members) {
		return zRem(key, members);
	}