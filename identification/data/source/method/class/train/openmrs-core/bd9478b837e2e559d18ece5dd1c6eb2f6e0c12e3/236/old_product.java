public Set<Encounter> getEncounters(Patient who, boolean includeVoided) {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_VIEW_ENCOUNTERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_VIEW_ENCOUNTERS);
		
		return getEncounterDAO().getEncounters(who, includeVoided);
	}