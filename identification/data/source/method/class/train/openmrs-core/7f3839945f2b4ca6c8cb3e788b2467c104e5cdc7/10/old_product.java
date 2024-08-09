@Transactional(readOnly=true)
	public List<Encounter> getEncountersByPatientId(Integer patientId, boolean includeVoided) throws APIException;