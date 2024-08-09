@Test
	@Verifies(value = "should fail validation if a preferred patient identifier is not chosen for voided patients", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfAPreferredPatientIdentifierIsNotChosenForVoidedPatients() throws Exception {
		Patient pa = Context.getPatientService().getPatient(432);
		
		Assert.assertTrue(pa.isVoided());//sanity check
		Assert.assertNotNull(pa.getPatientIdentifier());
		for (PatientIdentifier id : pa.getIdentifiers())
			id.setPreferred(false);
		
		Errors errors = new BindException(pa, "patient");
		validator.validate(pa, errors);
		Assert.assertTrue(errors.hasErrors());
	}