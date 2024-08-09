@Test
	public void unretireProvider_shouldUnretireAProvider() {
		Provider provider = service.getProvider(2);
		service.unretireProvider(provider);
		assertFalse(provider.getRetired());
		assertNull(provider.getRetireReason());
	}