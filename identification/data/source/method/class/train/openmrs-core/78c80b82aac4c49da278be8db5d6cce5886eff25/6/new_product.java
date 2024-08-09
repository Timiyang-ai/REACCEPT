@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_ENCOUNTER_TYPES })
	public EncounterType getEncounterType(Integer encounterTypeId)
	        throws APIException;