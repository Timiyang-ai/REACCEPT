public void validate(Object obj, Errors errors) {
		Patient patient = (Patient) obj;
		
		// Validate PatientIdentifers
		PatientIdentifierValidator piv = new PatientIdentifierValidator();
		if (patient != null && patient.getIdentifiers() != null) {
			for (PatientIdentifier identifier : patient.getIdentifiers()) {
				piv.validate(identifier, errors);
			}
		}
	}