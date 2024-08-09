public Map<EncounterRole, Set<Provider>> getProvidersByRoles(boolean includeVoided) {
		
		Map<EncounterRole, Set<Provider>> providers = new HashMap<EncounterRole, Set<Provider>>();
		for (EncounterProvider encounterProvider : encounterProviders) {
			Set<Provider> list = providers.get(encounterProvider.getEncounterRole());
			if (list == null) {
				list = new LinkedHashSet<Provider>();
				providers.put(encounterProvider.getEncounterRole(), list);
			}
			
			if (!includeVoided && encounterProvider.getVoided()) {
				continue;
			}
			
			list.add(encounterProvider.getProvider());
		}
		
		return providers;
	}