	@Test(expected = BlankIdentifierException.class)
	public void checkIdentifierAgainstFormat_shouldFailValidationIfIdentifierIsBlank() {
		PatientIdentifierValidator.validateIdentifier("", new PatientIdentifierType(1));
	}