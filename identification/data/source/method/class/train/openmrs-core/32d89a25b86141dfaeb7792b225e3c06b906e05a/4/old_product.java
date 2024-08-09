@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTERS })
	public List<Encounter> getEncountersNotAssignedToAnyVisit(Patient patient) throws APIException;