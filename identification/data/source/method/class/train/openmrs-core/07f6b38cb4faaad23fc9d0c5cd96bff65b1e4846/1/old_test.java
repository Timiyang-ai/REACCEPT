@Test
	public void getProviderbyUuid_shouldGetProviderGivenUuid() throws Exception {
		Provider provider = service.getProviderbyUuid("a2c3868a-6b90-11e0-93c3-18a905e044dc");
		Assert.assertNotNull(provider);
		assertEquals("RobertClive", provider.getName());
	}