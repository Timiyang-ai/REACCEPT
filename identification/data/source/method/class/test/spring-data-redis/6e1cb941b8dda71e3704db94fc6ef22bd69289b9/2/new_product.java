@Override
	public Long geoAdd(String key, Map<String, Point> memberCoordinateMap) {

		Assert.notNull(memberCoordinateMap, "MemberCoordinateMap must not be null!");

		Map<byte[], Point> byteMap = new HashMap<byte[], Point>();
		for (Entry<String, Point> entry : memberCoordinateMap.entrySet()) {
			byteMap.put(serialize(entry.getKey()), memberCoordinateMap.get(entry.getValue()));
		}

		return geoAdd(serialize(key), byteMap);
	}