public static void validateIdentifier(PatientIdentifier pi) throws PatientIdentifierException {
		
		// Validate that the identifier is non-null
		if (pi == null) {
			throw new BlankIdentifierException("Patient Identifier cannot be null.");
		}
		
		// Only validate if the PatientIdentifier is not voided
		if (!pi.isVoided()) {
			
			// Check is already in use by another patient
			if (Context.getPatientService().isIdentifierInUseByAnotherPatient(pi)) {
				throw new IdentifierNotUniqueException("Identifier " + pi.getIdentifier()
			        + " already in use by another patient");
			}
			
			// Check that this is a identifier is valid
			validateIdentifier(pi.getIdentifier(), pi.getIdentifierType());
		}
	}