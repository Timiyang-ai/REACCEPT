@Override
	public Long geoRemove(String key, String... members) {
		return geoRemove(serialize(key), serializeMulti(members));
	}