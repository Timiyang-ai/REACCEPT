public List<Encounter> getEncountersByPatientId(Integer patientId, boolean includeVoided) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_VIEW_ENCOUNTERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_VIEW_ENCOUNTERS);
		
		return getEncounterDAO().getEncountersByPatientId(patientId, includeVoided);
	}