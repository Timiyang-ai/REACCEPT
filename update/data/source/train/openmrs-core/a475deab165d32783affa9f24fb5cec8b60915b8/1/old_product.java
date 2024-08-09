public PatientIdentifierType unretirePatientIdentifierType(PatientIdentifierType patientIdentifierType)
	        throws APIException {
		patientIdentifierType.setRetired(false);
		patientIdentifierType.setRetiredBy(null);
		patientIdentifierType.setDateRetired(null);
		patientIdentifierType.setRetireReason(null);
		return Context.getPatientService().savePatientIdentifierType(patientIdentifierType);
	}