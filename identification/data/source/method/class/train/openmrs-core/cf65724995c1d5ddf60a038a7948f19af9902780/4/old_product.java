@Authorized( { OpenmrsConstants.PRIV_MANAGE_ENCOUNTER_TYPES })
	public EncounterType retireEncounterType(EncounterType encounterType, String reason) throws APIException;