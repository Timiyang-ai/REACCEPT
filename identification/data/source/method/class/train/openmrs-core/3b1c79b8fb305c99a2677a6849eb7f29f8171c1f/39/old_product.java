@Transactional(readOnly = true)
	public PatientState getPatientStateByUuid(String uuid);