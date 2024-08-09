@Test
	public void testGeoHash() {

		K key = keyFactory.instance();
		M v1 = valueFactory.instance();
		M v2 = valueFactory.instance();

		geoOperations.geoAdd(key, POINT_PALERMO, v1);
		geoOperations.geoAdd(key, POINT_CATANIA, v2);

		List<String> result = geoOperations.geoHash(key, v1, v2);
		assertThat(result, hasSize(2));

		final RedisSerializer<String> serializer = new StringRedisSerializer();

		assertThat(result.get(0), is(equalTo("sqc8b49rny0")));
		assertThat(result.get(1), is(equalTo("sqdtr74hyu0")));
	}