	@Test
	public void test_putAll()
	{
		final Map<String, String> valuesToPut = ImmutableMap.<String, String> builder()
				.put("key1", "value1")
				.put("key2", "value2")
				.build();

		final CCache<String, String> cache = new CCache<String, String>("Test", 10);
		assertEmpty(cache);

		cache.putAll(valuesToPut);
		assertSize(cache, valuesToPut.size());

		Assert.assertEquals("Value shall exist", "value1", cache.get("key1"));
		Assert.assertEquals("Value shall exist", "value2", cache.get("key2"));

		//
		// Test putAll shall override existing value
		cache.putAll(Collections.singletonMap("key1", "value1_newValue"));
		Assert.assertEquals("Value shall exist", "value1_newValue", cache.get("key1"));
	}