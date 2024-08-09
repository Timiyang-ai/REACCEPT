@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_ENCOUNTERS })
	public List<Encounter> getEncountersByPatientId(Integer patientId, boolean includeVoided) throws APIException;