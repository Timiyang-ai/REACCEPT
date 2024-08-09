	@Test
	public void getProviderByIdentifier_shouldGetAProviderMatchingTheSpecifiedIdentifierIgnoringCase() {
		String identifier = "8a760";
		Provider provider = service.getProviderByIdentifier(identifier);
		Assert.assertEquals("a2c3868a-6b90-11e0-93c3-18a905e044dc", provider.getUuid());
		//ensures that the case sensitive test stays valid just in case 
		//the test dataset is edited and the case is changed
		Assert.assertNotSame(identifier, provider.getIdentifier());
	}