public List<PatientIdentifierType> getPatientIdentifierTypes() throws APIException {
		return context.getDAOContext().getPatientDAO().getPatientIdentifierTypes();
	}