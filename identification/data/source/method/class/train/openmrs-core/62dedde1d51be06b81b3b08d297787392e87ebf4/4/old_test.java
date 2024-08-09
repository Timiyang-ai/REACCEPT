	@Test
	public void retireProvider_shouldRetireAProvider() {
		Provider provider = service.getProvider(1);
		assertFalse(provider.getRetired());
		assertNull(provider.getRetireReason());
		service.retireProvider(provider, "retire reason");
		assertTrue(provider.getRetired());
		assertEquals("retire reason", provider.getRetireReason());
		assertEquals(6, service.getAllProviders(false).size());
	}