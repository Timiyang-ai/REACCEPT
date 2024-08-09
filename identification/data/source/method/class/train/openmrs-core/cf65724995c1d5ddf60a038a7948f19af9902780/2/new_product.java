@Authorized( { OpenmrsConstants.PRIV_ADD_ENCOUNTERS,
	        OpenmrsConstants.PRIV_EDIT_ENCOUNTERS })
	public Encounter saveEncounter(Encounter encounter) throws APIException;