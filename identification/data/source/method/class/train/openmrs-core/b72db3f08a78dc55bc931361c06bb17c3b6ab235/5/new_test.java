	@Test
	public void getProvidersByRoles_shouldReturnAllRolesAndProviders() {
		//given
		Encounter encounter = new Encounter();
		EncounterRole role = new EncounterRole();
		
		Provider provider = new Provider();
		encounter.addProvider(role, provider);
		
		Provider provider2 = new Provider();
		encounter.addProvider(role, provider2);
		
		EncounterRole role2 = new EncounterRole();
		Provider provider3 = new Provider();
		encounter.addProvider(role2, provider3);
		
		//when
		Map<EncounterRole, Set<Provider>> providersByRoles = encounter.getProvidersByRoles();
		
		//then
		Assert.assertEquals("Roles", 2, providersByRoles.size());
		Assert.assertTrue("Roles", providersByRoles.keySet().containsAll(Arrays.asList(role, role2)));
		
		Assert.assertEquals("Providers for role", 2, providersByRoles.get(role).size());
		Assert.assertTrue("Providers for role", providersByRoles.get(role).containsAll(Arrays.asList(provider, provider2)));
		
		Assert.assertEquals("Provider for role2", 1, providersByRoles.get(role2).size());
		Assert.assertTrue("Providers for role2", providersByRoles.get(role2).contains(provider3));
	}