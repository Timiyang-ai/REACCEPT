public void removeProvider(EncounterRole role, Provider provider) {
		for (EncounterProvider encounterProvider : encounterProviders) {
			if (encounterProvider.getEncounterRole().equals(role) && encounterProvider.getProvider().equals(provider)) {
				encounterProvider.setVoided(true);
				encounterProvider.setDateVoided(new Date());
				encounterProvider.setVoidedBy(Context.getAuthenticatedUser());
				return;
			}
		}
	}