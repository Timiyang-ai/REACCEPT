@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	public List<Encounter> getEncountersByPatientId(Integer patientId, boolean includeVoided) throws APIException;