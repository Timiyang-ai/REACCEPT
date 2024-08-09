	@Test
	public void addProvider_shouldAddProviderForNewRole() {
		//given
		Encounter encounter = new Encounter();
		EncounterRole encounterRole = new EncounterRole();
		Provider provider = new Provider();
		
		//when
		encounter.addProvider(encounterRole, provider);
		
		//then
		Assert.assertTrue(encounter.getProvidersByRole(encounterRole).contains(provider));
	}