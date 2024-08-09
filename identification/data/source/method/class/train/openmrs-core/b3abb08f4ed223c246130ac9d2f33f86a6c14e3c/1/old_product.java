public static void checkIdentifierAgainstValidator(String identifier, IdentifierValidator validator)
	                                                                                                    throws PatientIdentifierException {
		
		log.debug("Checking identifier: " + identifier + " against validator: " + validator);
		
		if (StringUtils.isBlank(identifier)) {
			throw new BlankIdentifierException("Identifier cannot be null or blank");
		}
		
		if (validator == null) {
			log.debug("Validator is null, identifier passes.");
			return;
		}
		
		// Check identifier against IdentifierValidator
		try {
			if (!validator.isValid(identifier)) {
				throw new InvalidCheckDigitException("Invalid check digit for identifier: " + identifier);
			}
		}
		catch (UnallowedIdentifierException e) {
			throw new InvalidCheckDigitException("Identifier " + identifier + " is not appropriate for validation scheme "
			        + validator.getName());
		}
		log.debug("The identifier passed validation.");
		
	}