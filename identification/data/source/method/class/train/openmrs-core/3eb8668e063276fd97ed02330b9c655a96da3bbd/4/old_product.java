public void setProvider(EncounterRole role, Provider provider) {
		for (Iterator<EncounterProvider> it = encounterProviders.iterator(); it.hasNext();) {
			EncounterProvider encounterProvider = it.next();
			if (encounterProvider.getEncounterRole().equals(role)) {
				it.remove();
			}
		}
		addProvider(role, provider);
	}