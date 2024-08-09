	@Test
	public void getAllProviders_shouldGetAllProviders() {
		List<Provider> providers = service.getAllProviders();
		assertEquals(9, providers.size());
	}