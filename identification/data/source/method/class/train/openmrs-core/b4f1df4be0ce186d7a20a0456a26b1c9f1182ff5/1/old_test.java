@Test
	public void getProviders_shouldReturnAllProvidersIfQueryIsEmpty() throws Exception {
		//given
		List<Provider> allProviders = service.getAllProviders();
		
		//when
		List<Provider> providers = service.getProviders("", null, null, null);
		
		//then
		Assert.assertEquals(allProviders.size(), providers.size());
	}