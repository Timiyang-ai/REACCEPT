public PatientIdentifierType getPatientIdentifierType(Integer patientIdentifierTypeId) throws APIException {
		return context.getDAOContext().getPatientDAO().getPatientIdentifierType(patientIdentifierTypeId);
	}