	@Test
	public void getUnknownProvider_shouldGetTheUnknownProviderAccount() {
		Provider provider = new Provider();
		
		provider.setPerson(newPerson("Unknown Provider"));
		
		provider.setIdentifier("Test Unknown Provider");
		provider = service.saveProvider(provider);
		GlobalProperty gp = new GlobalProperty(OpenmrsConstants.GP_UNKNOWN_PROVIDER_UUID, provider.getUuid(), null);
		Context.getAdministrationService().saveGlobalProperty(gp);
		assertEquals(provider, service.getUnknownProvider());
	}