	@Test
	public void getProviders_shouldFetchProviderWithGivenNameWithCaseInSensitive() {
		List<Provider> providers = service.getProviders("colle", 0, null, null);
		assertEquals(1, providers.size());
	}