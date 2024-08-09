	@Test
	public void getProvidersByRole_shouldReturnEmptySetForNoRole() {
		//given
		Encounter encounter = new Encounter();
		EncounterRole role = new EncounterRole();
		Provider provider = new Provider();
		encounter.addProvider(role, provider);
		
		EncounterRole role2 = new EncounterRole();
		
		//when
		Set<Provider> providers = encounter.getProvidersByRole(role2);
		
		//then
		Assert.assertEquals(0, providers.size());
	}