public void addProvider(EncounterRole role, Provider provider) {
		EncounterProvider encounterProvider = new EncounterProvider();
		encounterProvider.setEncounter(this);
		encounterProvider.setEncounterRole(role);
		encounterProvider.setProvider(provider);
		encounterProviders.add(encounterProvider);
	}