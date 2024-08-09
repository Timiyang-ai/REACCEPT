@Test
	public void getProviders_shouldReturnAllProvidersIfQueryIsEmptyAndIncludeRetiredTrue() throws Exception {
		//given
		List<Provider> allProviders = service.getAllProviders();
		
		//when
		List<Provider> providers = service.getProviders("", null, null, null, true);
		
		//then
		Assert.assertEquals(allProviders.size(), providers.size());
	}