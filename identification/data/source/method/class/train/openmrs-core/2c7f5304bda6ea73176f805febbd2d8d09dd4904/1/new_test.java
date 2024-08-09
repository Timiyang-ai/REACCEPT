	@Test(expected = BlankIdentifierException.class)
	public void validateIdentifier_shouldFailValidationIfPatientIdentifierIsNull() {
		PatientIdentifierValidator.validateIdentifier(null);
	}