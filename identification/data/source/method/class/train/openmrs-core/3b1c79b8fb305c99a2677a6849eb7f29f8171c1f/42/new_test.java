	@Test
	public void getProviderByUuid_shouldGetProviderGivenUuid() {
		Provider provider = service.getProviderByUuid("ba4781f4-6b94-11e0-93c3-18a905e044dc");
		assertEquals("Collet Test Chebaskwony", provider.getName());
		assertNotNull(provider);
	}