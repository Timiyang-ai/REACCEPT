@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTERS })
	public List<Encounter> getEncountersByPatientId(Integer patientId, boolean includeVoided) throws APIException;