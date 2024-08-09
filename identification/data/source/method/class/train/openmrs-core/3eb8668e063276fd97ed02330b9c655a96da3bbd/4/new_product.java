public void setProvider(EncounterRole role, Provider provider) {
		boolean hasProvider = false;
		for (Iterator<EncounterProvider> it = encounterProviders.iterator(); it.hasNext();) {
			EncounterProvider encounterProvider = it.next();
			if (encounterProvider.getEncounterRole().equals(role)) {
				if (!encounterProvider.getProvider().equals(provider)) {
					it.remove();
				} else {
					hasProvider = true;
				}
			}
		}
		
		if (!hasProvider) {
			addProvider(role, provider);
		}
	}