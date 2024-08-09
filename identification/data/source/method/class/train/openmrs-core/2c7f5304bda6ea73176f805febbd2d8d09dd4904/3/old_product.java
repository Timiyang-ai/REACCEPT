public static void validateIdentifier(String identifier, PatientIdentifierType pit) throws PatientIdentifierException {
		
		log.debug("Checking identifier: " + identifier + " for type: " + pit);
		
		// Validate input parameters
		if (pit == null) {
			throw new BlankIdentifierException("Patient Identifier Type cannot be null");
		}
		if (StringUtils.isBlank(identifier)) {
			throw new BlankIdentifierException("Identifier cannot be null or blank");
		}
		
		checkIdentifierAgainstFormat(identifier, pit.getFormat());
		
		// Check identifier against IdentifierValidator
		if (pit.hasValidator()) {
			IdentifierValidator validator = Context.getPatientService().getIdentifierValidator(pit.getValidator());
			checkIdentifierAgainstValidator(identifier, validator);
		}
		log.debug("The identifier check was successful");
		
	}