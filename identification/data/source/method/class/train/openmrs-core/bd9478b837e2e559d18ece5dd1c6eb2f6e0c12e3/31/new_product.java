public void unvoidPatient(Patient patient) throws APIException {
		getPatientDAO().unvoidPatient(patient);
	}