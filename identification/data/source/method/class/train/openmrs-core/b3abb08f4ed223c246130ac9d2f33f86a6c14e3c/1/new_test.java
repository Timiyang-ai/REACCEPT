	@Test(expected = BlankIdentifierException.class)
	public void checkIdentifierAgainstValidator_shouldFailValidationIfIdentifierIsBlank() {
		PatientIdentifierValidator.checkIdentifierAgainstValidator("", new LuhnIdentifierValidator());
	}