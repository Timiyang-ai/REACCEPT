@Override
	public Long geoRemove(String key, String... members) {
		return zRem(key, members);
	}