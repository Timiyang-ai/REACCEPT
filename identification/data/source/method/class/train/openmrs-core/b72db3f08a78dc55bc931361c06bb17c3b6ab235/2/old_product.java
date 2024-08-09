public Set<Provider> getProvidersByRole(EncounterRole role, boolean includeVoided) {
		Set<Provider> providers = new LinkedHashSet<Provider>();
		
		for (EncounterProvider encounterProvider : encounterProviders) {
			if (encounterProvider.getEncounterRole().equals(role)) {
				if (!includeVoided && encounterProvider.getVoided()) {
					continue;
				}
				
				providers.add(encounterProvider.getProvider());
			}
		}
		
		return providers;
	}