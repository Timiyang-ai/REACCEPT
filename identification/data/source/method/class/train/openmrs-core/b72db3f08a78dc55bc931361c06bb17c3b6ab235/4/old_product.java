public void removeProvider(EncounterRole role, Provider provider) {
		for (EncounterProvider encounterProvider : encounterProviders) {
			if (encounterProvider.getEncounterRole().equals(role) && encounterProvider.getProvider().equals(provider)) {
				encounterProviders.remove(encounterProvider);
				return;
			}
		}
	}