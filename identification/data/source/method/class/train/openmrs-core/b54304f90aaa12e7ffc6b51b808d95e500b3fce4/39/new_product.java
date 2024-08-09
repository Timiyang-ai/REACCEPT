@Override
	public String getValidIdentifier(String undecoratedIdentifier) throws UnallowedIdentifierException {
		
		String result = super.getValidIdentifier(undecoratedIdentifier);
		
		if (undecoratedIdentifier.length() != VERHOEFF_UNDECORATED_ID_LENGTH) {
			throw new UnallowedIdentifierException("Undecorated identifier must be " + VERHOEFF_UNDECORATED_ID_LENGTH
			        + " digits long.");
		}
		
		return result;
	}