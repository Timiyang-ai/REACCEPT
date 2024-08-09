public PatientIdentifierType getPatientIdentifierType(Integer patientIdentifierTypeId) throws APIException {
		return getPatientDAO().getPatientIdentifierType(patientIdentifierTypeId);
	}