public Collection<Encounter> getEncounters(Location loc, Date fromDate, Date toDate) {
    	if (!context.hasPrivilege(OpenmrsConstants.PRIV_VIEW_ENCOUNTERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_VIEW_ENCOUNTERS);
		
		return getEncounterDAO().getEncounters(loc, fromDate, toDate);
    }