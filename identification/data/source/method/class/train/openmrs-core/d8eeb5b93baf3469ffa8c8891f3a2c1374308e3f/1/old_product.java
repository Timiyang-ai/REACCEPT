@Override
	public boolean isValid(String identifier) throws UnallowedIdentifierException {
		
		boolean result = super.isValid(identifier);
		
		if (Character.isDigit(identifier.charAt(identifier.length() - 1)))
			throw new UnallowedIdentifierException("Check digit can not be numeric.");
		if (identifier.length() != VERHOEFF_ID_LENGTH)
			throw new UnallowedIdentifierException("Identifier must be " + VERHOEFF_ID_LENGTH + " digits long.");
		
		return result;
	}