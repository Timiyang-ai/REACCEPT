@Test
	public void getProviderByUuid_shouldGetProviderGivenUuid() throws Exception {
		Provider provider = service.getProviderByUuid("a2c3868a-6b90-11e0-93c3-18a905e044dc");
		Assert.assertNotNull(provider);
		assertEquals("RobertClive", provider.getName());
	}