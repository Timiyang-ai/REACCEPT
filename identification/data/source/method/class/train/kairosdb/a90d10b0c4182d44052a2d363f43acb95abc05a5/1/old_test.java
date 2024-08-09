	@Test
	public void test_isCached()
	{
		DataCache<String> cache = new DataCache<String>(3);

		assertNull(cache.cacheItem("one"));
		assertNull(cache.cacheItem("two"));
		assertNull(cache.cacheItem("three"));

		assertNotNull(cache.cacheItem("one")); //This puts 'one' as the newest
		assertNull(cache.cacheItem("four")); //This should boot out 'two'
		assertNull(cache.cacheItem("two")); //Should have booted 'three'
		assertNotNull(cache.cacheItem("one"));
		assertNull(cache.cacheItem("three")); //Should have booted 'four'
		assertNotNull(cache.cacheItem("one"));
	}