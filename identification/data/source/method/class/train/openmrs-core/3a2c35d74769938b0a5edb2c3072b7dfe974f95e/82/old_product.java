@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	public List<Encounter> getEncountersByPatientIdentifier(String identifier, boolean includeVoided) throws APIException;