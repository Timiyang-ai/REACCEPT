public Map<EncounterRole, Set<Provider>> getProvidersByRoles(boolean includeVoided) {
		
		return encounterProviders.stream()
				.filter(ep -> includeVoided || !ep.getVoided())
				.collect(Collectors.groupingBy(EncounterProvider::getEncounterRole, Collectors.mapping(EncounterProvider::getProvider, Collectors.toSet())));
		
	}