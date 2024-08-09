public void voidPatient(Patient patient, String reason) throws APIException {
		context.getDAOContext().getPatientDAO().voidPatient(patient, reason);
	}