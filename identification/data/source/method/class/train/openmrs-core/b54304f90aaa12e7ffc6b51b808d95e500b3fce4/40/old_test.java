	@Test
	public void setAttribute_shouldVoidTheAttributeIfAnAttributeWithSameAttributeTypeAlreadyExistsAndTheMaxOccursIsSetTo1()
	{
		Provider provider = new Provider();
		provider.setIdentifier("test");
		
		provider.setPerson(newPerson("name"));
		
		ProviderAttributeType place = service.getProviderAttributeType(3);
		provider.setAttribute(buildProviderAttribute(place, "bangalore"));
		provider.setAttribute(buildProviderAttribute(place, "chennai"));
		
		Assert.assertEquals(1, provider.getAttributes().size());
		
		service.saveProvider(provider);
		Assert.assertNotNull(provider.getId());
		
		provider.setAttribute(buildProviderAttribute(place, "seattle"));
		Assert.assertEquals(2, provider.getAttributes().size());
		ProviderAttribute lastAttribute = (ProviderAttribute) provider.getAttributes().toArray()[0];
		Assert.assertTrue(lastAttribute.getVoided());
	}