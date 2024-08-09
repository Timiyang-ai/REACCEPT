	@Test
	public void validate_shouldFailValidationIfAPreferredPatientIdentifierIsNotChosen() {
		Patient pa = Context.getPatientService().getPatient(2);
		Assert.assertNotNull(pa.getPatientIdentifier());
		//set all identifiers to be non-preferred
		for (PatientIdentifier id : pa.getIdentifiers())
			id.setPreferred(false);
		
		Errors errors = new BindException(pa, "patient");
		validator.validate(pa, errors);
		Assert.assertTrue(errors.hasErrors());
	}