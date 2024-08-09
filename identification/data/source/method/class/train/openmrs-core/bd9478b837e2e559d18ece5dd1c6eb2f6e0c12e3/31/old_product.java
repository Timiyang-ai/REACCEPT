public void unvoidPatient(Patient patient) throws APIException {
		context.getDAOContext().getPatientDAO().unvoidPatient(patient);
	}