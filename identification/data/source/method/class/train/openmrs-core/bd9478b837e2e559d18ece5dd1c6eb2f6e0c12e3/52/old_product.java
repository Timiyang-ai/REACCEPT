public Patient getPatient(Integer patientId) throws APIException {
		return context.getDAOContext().getPatientDAO().getPatient(patientId);
	}