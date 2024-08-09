	@Test
	public void test_clear()
	{
		final CCache<String, String> cache = new CCache<String, String>("Test", 10);
		Assert.assertEquals("invalid isJustReset", true, cache.isReset());
		assertEmpty(cache);

		// Add one element and test
		testPutGet(cache, "key1", "value1");
		assertSize(cache, 1);

		// Clear
		cache.reset();
		Assert.assertEquals("invalid isJustReset", true, cache.isReset());
		assertEmpty(cache);
	}