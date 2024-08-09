	@Test
	public void validate_shouldPassValidationIfFieldLengthsAreCorrect() {
		PatientIdentifier pi = new PatientIdentifier("1TU-8", new PatientIdentifierType(1), null);
		PatientIdentifierType idType = pi.getIdentifierType();
		idType.setLocationBehavior(PatientIdentifierType.LocationBehavior.NOT_USED);
		pi.setVoidReason("voidReason");
		
		Errors errors = new BindException(pi, "pi");
		new PatientIdentifierValidator().validate(pi, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}