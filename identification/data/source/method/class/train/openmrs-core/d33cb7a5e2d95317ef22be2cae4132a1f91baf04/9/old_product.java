public void unvoidEncounter(Encounter encounter) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_EDIT_ENCOUNTERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_EDIT_ENCOUNTERS);
		
		String voidReason = encounter.getVoidReason();
		if (voidReason == null)
			voidReason = "";
		
		encounter.setVoided(false);
		encounter.setVoidedBy(null);
		encounter.setDateVoided(null);
		encounter.setVoidReason(null);
		updateEncounter(encounter);
	}