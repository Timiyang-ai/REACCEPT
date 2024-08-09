public Set<Provider> getProvidersByRole(EncounterRole role, boolean includeVoided) {
		
		return encounterProviders.stream()
				.filter(ep -> ep.getEncounterRole().equals(role) && (includeVoided || !ep.getVoided()))
				.map(EncounterProvider::getProvider)
				.collect(Collectors.toSet());
	}