@Test
	public void setAttribute_shouldVoidTheAttributeIfAnAttributeWithSameAttributeTypeAlreadyExistsAndTheMaxOccursIsSetTo1()
	        throws Exception {
		Provider provider = new Provider();
		provider.setIdentifier("test");
		provider.setName("test provider");
		AttributeType<Provider> providerAttributeType = service.getProviderAttributeType(3);
		provider.setAttribute(createProviderAttribute(providerAttributeType, "bangalore"));
		provider.setAttribute(createProviderAttribute(providerAttributeType, "chennai"));
		Assert.assertEquals(1, provider.getAttributes().size());
		service.saveProvider(provider);
		Assert.assertNotNull(provider.getId());
		provider.setAttribute(createProviderAttribute(providerAttributeType, "seattle"));
		Assert.assertEquals(2, provider.getAttributes().size());
		ProviderAttribute lastAttribute = (ProviderAttribute) provider.getAttributes().toArray()[0];
		Assert.assertTrue(lastAttribute.getVoided());
	}