	@Test
	public void validate_shouldPassValidationIfFieldLengthsAreCorrect() {
		HL7Source hl7Source = new HL7Source();
		hl7Source.setName("name");
		
		Errors errors = new BindException(hl7Source, "hl7Source");
		new HL7SourceValidator().validate(hl7Source, errors);
		Assert.assertFalse(errors.hasErrors());
	}