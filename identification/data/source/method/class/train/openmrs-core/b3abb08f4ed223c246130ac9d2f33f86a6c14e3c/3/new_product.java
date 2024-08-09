public static void checkIdentifierAgainstFormat(String identifier, String format, String formatDescription) throws PatientIdentifierException {
		
		log.debug("Checking identifier: " + identifier + " against format: " + format);
		
		if (StringUtils.isBlank(identifier)) {
			throw new BlankIdentifierException("PatientIdentifier.error.nullOrBlank");
		}
		
		if (StringUtils.isBlank(format)) {
			log.debug("Format is blank, identifier passes.");
			return;
		}
		
		// Check identifier against regular expression format
		if (!identifier.matches(format)) {
			log.debug("The two DO NOT match");
			throw new InvalidIdentifierFormatException(getMessage("PatientIdentifier.error.invalidFormat", identifier,
					StringUtils.isNotBlank(formatDescription) ? formatDescription : format));
		}
		log.debug("The two match!!");
	}