@Override
	public List<String> geoHash(byte[] key, byte[]... members) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(members, "Members must not be null!");
		Assert.noNullElements(members, "Members must not contain null!");

		try {
			if (isPipelined()) {
				pipeline(new LettuceResult(getAsyncConnection().geohash(key, members)));
				return null;
			}
			if (isQueueing()) {
				transaction(new LettuceTxResult(getConnection().geohash(key, members)));
				return null;
			}
			return getConnection().geohash(key, members);
		} catch (Exception ex) {
			throw convertLettuceAccessException(ex);
		}
	}