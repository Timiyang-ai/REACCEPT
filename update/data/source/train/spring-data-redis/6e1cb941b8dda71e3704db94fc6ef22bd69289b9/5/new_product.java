@Override
	public Distance geoDist(String key, String member1, String member2, Metric metric) {
		return geoDist(serialize(key), serialize(member1), serialize(member2), metric);
	}