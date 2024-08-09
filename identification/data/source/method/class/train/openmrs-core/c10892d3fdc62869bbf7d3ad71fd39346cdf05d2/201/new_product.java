@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	public List<Encounter> getEncountersByPatient(String query, boolean includeVoided) throws APIException;