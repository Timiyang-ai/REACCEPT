@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_ENCOUNTERS })
	public Encounter getEncounterByUuid(String uuid) throws APIException;