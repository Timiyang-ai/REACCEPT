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
		// we need to cheat and use reflection to look at the private encounterProviders property; we don't want the getProvidersByRole method hiding duplicates from us
		Collection<EncounterProvider> providers = (Collection<EncounterProvider>) FieldUtils.readField(encounter,
		    "encounterProviders", true);
		Assert.assertEquals(1, providers.size());
		Assert.assertTrue(encounter.getProvidersByRole(role).contains(provider1));
	}