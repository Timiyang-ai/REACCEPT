public void addProvider(EncounterRole role, Provider provider) {
		// first, make sure the provider isn't already there
		for (EncounterProvider ep : encounterProviders) {
			if (ep.getEncounterRole().equals(role) && ep.getProvider().equals(provider) && !ep.getVoided()) {
				return;
			}
		}
		EncounterProvider encounterProvider = new EncounterProvider();
		encounterProvider.setEncounter(this);
		encounterProvider.setEncounterRole(role);
		encounterProvider.setProvider(provider);
		encounterProvider.setDateCreated(new Date());
		encounterProvider.setCreator(Context.getAuthenticatedUser());
		encounterProviders.add(encounterProvider);
	}