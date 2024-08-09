public Set<Provider> getProvidersByRole(EncounterRole role) {
		Set<Provider> providers = new LinkedHashSet<Provider>();
		for (EncounterProvider encounterProvider : encounterProviders) {
			if (encounterProvider.getEncounterRole().equals(role)) {
				providers.add(encounterProvider.getProvider());
			}
		}
		return providers;
	}