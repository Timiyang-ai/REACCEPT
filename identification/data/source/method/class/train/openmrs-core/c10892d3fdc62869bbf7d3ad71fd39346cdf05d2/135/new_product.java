@Authorized( { PrivilegeConstants.VIEW_ENCOUNTERS })
	public List<Encounter> getEncountersByPatient(String query) throws APIException;