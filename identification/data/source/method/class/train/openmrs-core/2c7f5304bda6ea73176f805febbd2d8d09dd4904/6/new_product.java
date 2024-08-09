public static void validateIdentifier(String identifier, PatientIdentifierType pit) throws PatientIdentifierException {
		
		log.debug("Checking identifier: " + identifier + " for type: " + pit);
		
		// Validate input parameters
		if (pit == null) {
			throw new BlankIdentifierException("PatientIdentifierType.null");
		}
		if (StringUtils.isBlank(identifier)) {
			throw new BlankIdentifierException("PatientIdentifier.error.nullOrBlank");
		}
		
		checkIdentifierAgainstFormat(identifier, pit.getFormat(), pit.getFormatDescription());
		
		// Check identifier against IdentifierValidator
		if (pit.hasValidator()) {
			IdentifierValidator validator = Context.getPatientService().getIdentifierValidator(pit.getValidator());
			checkIdentifierAgainstValidator(identifier, validator);
		}
		log.debug("The identifier check was successful");
		
	}