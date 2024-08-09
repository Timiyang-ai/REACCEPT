public void setProvider(EncounterRole role, Provider provider) {
		boolean hasProvider = false;
		for (EncounterProvider encounterProvider : encounterProviders) {
			if (encounterProvider.getEncounterRole().equals(role)) {
				if (!encounterProvider.getProvider().equals(provider)) {
					encounterProvider.setVoided(true);
					encounterProvider.setDateVoided(new Date());
					encounterProvider.setVoidedBy(Context.getAuthenticatedUser());
				} else if (!encounterProvider.getVoided()) {
					hasProvider = true;
				}
			}
		}
		
		if (!hasProvider) {
			addProvider(role, provider);
		}
	}