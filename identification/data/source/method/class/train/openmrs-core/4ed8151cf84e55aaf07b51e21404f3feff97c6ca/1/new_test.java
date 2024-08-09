@Test
	public void getProviders_shouldReturnRetiredProvidersByDefault() throws Exception {
		List<Provider> providers = service.getProviders(null, null, null, null);
		Assert.assertEquals(8, providers.size());
	}