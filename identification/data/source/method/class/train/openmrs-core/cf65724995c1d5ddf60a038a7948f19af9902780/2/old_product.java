@Authorized( { OpenmrsConstants.PRIV_ADD_ENCOUNTERS,
	        OpenmrsConstants.PRIV_EDIT_ENCOUNTERS })
	public void saveEncounter(Encounter encounter) throws APIException;