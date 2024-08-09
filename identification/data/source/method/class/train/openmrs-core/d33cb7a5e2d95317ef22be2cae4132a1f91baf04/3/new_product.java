public void voidEncounter(Encounter encounter, String reason) {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_EDIT_ENCOUNTERS))
			throw new APIAuthenticationException("Privilege required: "
					+ OpenmrsConstants.PRIV_EDIT_ENCOUNTERS);

		if (reason == null)
			reason = "";
		
		ObsService os = context.getObsService();
		for (Obs o : encounter.getObs()) {
			if (!o.isVoided()) {
				os.voidObs(o, reason);
			}
		}
		
		encounter.setVoided(true);
		encounter.setVoidedBy(context.getAuthenticatedUser());
		encounter.setDateVoided(new Date());
		encounter.setVoidReason(reason);
		updateEncounter(encounter);
	}