public Patient getPatient(Integer patientId) throws APIException {
		return getPatientDAO().getPatient(patientId);
	}