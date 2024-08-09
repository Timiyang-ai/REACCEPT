public void voidPatient(Patient patient, String reason) throws APIException {
		getPatientDAO().voidPatient(patient, reason);
	}