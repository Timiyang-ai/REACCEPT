	@Test
	public void purgeProvider_shouldDeleteAProvider() {
		Provider provider = service.getProvider(2);
		service.purgeProvider(provider);
		assertEquals(8, Context.getProviderService().getAllProviders().size());
	}