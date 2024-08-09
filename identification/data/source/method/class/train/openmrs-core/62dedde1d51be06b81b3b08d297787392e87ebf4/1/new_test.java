	@Test
	public void saveProvider_shouldSaveAProviderWithPersonAlone() {
		Provider provider = new Provider();
		provider.setIdentifier("unique");
		Person person = Context.getPersonService().getPerson(999);
		provider.setPerson(person);
		service.saveProvider(provider);
		Assert.assertNotNull(provider.getId());
		Assert.assertNotNull(provider.getUuid());
		Assert.assertNotNull(provider.getCreator());
		Assert.assertNotNull(provider.getDateCreated());
		Assert.assertEquals(999, provider.getPerson().getId().intValue());
		
	}