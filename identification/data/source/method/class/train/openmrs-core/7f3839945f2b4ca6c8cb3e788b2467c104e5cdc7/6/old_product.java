public List<Encounter> getEncountersByPatientId(Integer patientId, boolean includeVoided) throws APIException {
		return getEncounterDAO().getEncountersByPatientId(patientId, includeVoided);
	}