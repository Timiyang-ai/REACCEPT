	@Test
	public void removeProvider_shouldVoidExistingEncounterProvider() {
		Encounter encounter = new Encounter();
		EncounterRole role = new EncounterRole();
		Provider provider = new Provider();
		
		encounter.addProvider(role, provider);
		
		Assert.assertEquals(1, encounter.getProvidersByRole(role).size());
		Assert.assertTrue(encounter.getProvidersByRole(role).contains(provider));
		
		encounter.removeProvider(role, provider);
		
		//the size should be 0 for non voided providers
		Assert.assertEquals(0, encounter.getProvidersByRole(role).size());
		
		//the size should be 1 if we include voided providers
		Assert.assertEquals(1, encounter.getProvidersByRole(role, true).size());
		
		//should contain the voided provider
		Assert.assertTrue(encounter.getProvidersByRole(role, true).contains(provider));
	}