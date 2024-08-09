@Test
	@Verifies(value = "should pass validation if gender is blank for Persons", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfGenderIsBlankForPersons() throws Exception {
		Person person = new Person(1);
		Errors errors = new BindException(person, "person");
		PersonValidator personValidator = new PersonValidator();
		personValidator.validate(person, errors);
		
		Assert.assertFalse(errors.hasFieldErrors("gender"));
	}