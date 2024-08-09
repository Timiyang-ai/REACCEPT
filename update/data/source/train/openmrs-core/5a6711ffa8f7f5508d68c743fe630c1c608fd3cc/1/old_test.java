@Test
	public void addProvider_shouldNotAddSameProviderTwiceForRole() throws Exception {
		//given
		Encounter encounter = new Encounter();
		EncounterRole role = new EncounterRole();
		Provider provider1 = new Provider();
		
		//when
		encounter.addProvider(role, provider1);
		encounter.addProvider(role, provider1);
		
		//then
		Assert.assertEquals(1, encounter.getProvidersByRole(role).size());
		Assert.assertTrue(encounter.getProvidersByRole(role).contains(provider1));
	}