@Test
	@Verifies(value = "should pass if the minimum required fields are provided and are valid", method = "validate(Object,Errors)")
	public void validate_shouldPassIfTheMinimumRequiredFieldsAreProvidedAndAreValid() throws Exception {
		Patient p = new Patient();
		p.setGender("M");
		Calendar c = Calendar.getInstance();
		c.set(1950, 3, 3);
		p.setBirthdate(c.getTime());
		p.addName(new PersonName("hor", null, "ty"));
		p.addIdentifier(new PatientIdentifier("hiuh", new PatientIdentifierType(2), new Location(1)));
		ShortPatientModel model = new ShortPatientModel(p);
		Errors errors = new BindException(model, "patientModel");
		validator.validate(model, errors);
		Assert.assertEquals(false, errors.hasErrors());
		
	}