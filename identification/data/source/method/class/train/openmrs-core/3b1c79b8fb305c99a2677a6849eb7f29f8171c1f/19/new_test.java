	@Test
	public void getProvider_shouldGetProviderGivenID() {
		Provider provider = service.getProvider(2);
		assertEquals("Mr. Horatio Test Hornblower", provider.getName());
	}