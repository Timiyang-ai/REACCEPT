@Authorized( { PrivilegeConstants.PURGE_ENCOUNTERS })
	public void purgeEncounter(Encounter encounter, boolean cascade) throws APIException;