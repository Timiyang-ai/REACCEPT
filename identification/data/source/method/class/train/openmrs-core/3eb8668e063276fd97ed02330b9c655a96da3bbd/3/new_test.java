	@Test
	public void setProvider_shouldClearProvidersAndSetProviderForRole() {
		//given
		Encounter encounter = new Encounter();
		EncounterRole role = new EncounterRole();
		
		Provider provider = new Provider();
		encounter.addProvider(role, provider);
		
		Provider provider2 = new Provider();
		encounter.addProvider(role, provider2);
		
		Provider provider3 = new Provider();
		
		//when
		encounter.setProvider(role, provider3);
		
		//then
		Assert.assertEquals(1, encounter.getProvidersByRole(role).size());
		Assert.assertTrue(encounter.getProvidersByRole(role).contains(provider3));
	}