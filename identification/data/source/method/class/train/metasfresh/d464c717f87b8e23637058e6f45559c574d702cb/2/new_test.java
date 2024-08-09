	@Test
	public void test_get_LoaderReturnsNull()
	{
		CCache<String, String> cache = new CCache<String, String>("Test", 10);
		final String value = cache.get("key1", new Supplier<String>()
		{

			@Override
			public String get()
			{
				return null;
			}
		});

		Assert.assertNull(value);
		Assert.assertFalse("value shall not be cached because supplier returned null", cache.containsKey("key1"));
	}