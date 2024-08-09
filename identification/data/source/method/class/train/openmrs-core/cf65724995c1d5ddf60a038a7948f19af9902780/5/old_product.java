@Authorized( { OpenmrsConstants.PRIV_MANAGE_ENCOUNTER_TYPES })
	public void retireEncounterType(EncounterType encounterType, String reason)
	        throws APIException;