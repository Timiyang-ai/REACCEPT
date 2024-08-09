@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTERS })
	public Encounter getEncounter(Integer encounterId) throws APIException;