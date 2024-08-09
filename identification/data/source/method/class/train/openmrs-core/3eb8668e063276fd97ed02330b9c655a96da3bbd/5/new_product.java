@Deprecated
	public void setProvider(Person provider) {
		EncounterRole unknownRole = Context.getEncounterService().getEncounterRoleByUuid(
		    EncounterRole.UNKNOWN_ENCOUNTER_ROLE_UUID);
		if (unknownRole == null) {
			throw new IllegalStateException("No 'Unknown' encounter role with uuid "
			        + EncounterRole.UNKNOWN_ENCOUNTER_ROLE_UUID + ".");
		}
		Collection<Provider> providers = Context.getProviderService().getProvidersByPerson(provider);
		if (providers == null || providers.isEmpty()) {
			throw new IllegalArgumentException("No provider with personId " + provider.getPersonId());
		}
		setProvider(unknownRole, providers.iterator().next());
	}