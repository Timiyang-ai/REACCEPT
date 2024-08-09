@Test
	public void getProviders_shouldNotReturnRetiredProviders() throws Exception {
		List<Provider> providers = service.getProviders(null, null, null, null);
		Assert.assertEquals(7, providers.size());
	}