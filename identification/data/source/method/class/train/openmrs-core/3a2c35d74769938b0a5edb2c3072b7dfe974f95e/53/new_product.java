@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTERS })
	public List<Encounter> getEncountersByPatientIdentifier(String identifier, boolean includeVoided) throws APIException;