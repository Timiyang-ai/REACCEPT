	@Test
	public void getIdentifierValidator_shouldReturnPatientIdentifierValidatorGivenClassName() throws Exception {
		IdentifierValidator identifierValidator = Context.getPatientService().getIdentifierValidator(
		    "org.openmrs.patient.impl.LuhnIdentifierValidator");
		Assert.assertNotNull(identifierValidator);
		Assert.assertEquals("Luhn CheckDigit Validator", identifierValidator.getName());
		
		identifierValidator = Context.getPatientService().getIdentifierValidator(
		    "org.openmrs.patient.impl.VerhoeffIdentifierValidator");
		Assert.assertNotNull(identifierValidator);
		Assert.assertEquals("Verhoeff Check Digit Validator.", identifierValidator.getName());
	}