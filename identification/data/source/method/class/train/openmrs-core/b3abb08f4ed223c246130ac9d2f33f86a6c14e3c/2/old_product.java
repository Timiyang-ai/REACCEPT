public static void checkIdentifierAgainstFormat(String identifier, String format) throws PatientIdentifierException {
		
		log.debug("Checking identifier: " + identifier + " against format: " + format);
		
		if (StringUtils.isBlank(identifier)) {
			throw new BlankIdentifierException("Identifier cannot be null or blank");
		}
		
		if (StringUtils.isBlank(format)) {
			log.debug("Format is blank, identifier passes.");
			return;
		}
		
		// Check identifier against regular expression format
		if (!identifier.matches(format)) {
			log.debug("The two DO NOT match");
			throw new InvalidIdentifierFormatException("Identifier [" + identifier + "] does not match required format: "
			        + format);
		}
		log.debug("The two match!!");
	}