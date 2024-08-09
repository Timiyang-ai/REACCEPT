public PatientIdentifierType unretirePatientIdentifierType(PatientIdentifierType patientIdentifierType)
	        throws APIException {
		checkIfPatientIdentifierTypesAreLocked();
		patientIdentifierType.setRetired(false);
		patientIdentifierType.setRetiredBy(null);
		patientIdentifierType.setDateRetired(null);
		patientIdentifierType.setRetireReason(null);
		return Context.getPatientService().savePatientIdentifierType(patientIdentifierType);
	}