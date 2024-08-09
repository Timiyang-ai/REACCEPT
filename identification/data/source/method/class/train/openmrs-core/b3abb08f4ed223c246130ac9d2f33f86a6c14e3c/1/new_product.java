public static void checkIdentifierAgainstValidator(String identifier,
			IdentifierValidator validator) throws PatientIdentifierException {

		log.debug("Checking identifier: " + identifier + " against validator: "
				+ validator);

		if (StringUtils.isBlank(identifier)) {
			throw new BlankIdentifierException(
					"PatientIdentifier.error.nullOrBlank");
		}

		if (validator == null) {
			log.debug("Validator is null, identifier passes.");
			return;
		}

		// Check identifier against IdentifierValidator
		try {
			if (!validator.isValid(identifier)) {
				throw new InvalidCheckDigitException(getMessage(
						"PatientIdentifier.error.checkDigitWithParameter",
						identifier));
			}
		} catch (UnallowedIdentifierException e) {
			throw new InvalidCheckDigitException(getMessage(
					"PatientIdentifier.error.unallowedIdentifier", identifier,
					validator.getName()));
		}
		log.debug("The identifier passed validation.");

	}