@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_ENCOUNTERS })
	public Encounter getEncounter(Integer encounterId) throws APIException;