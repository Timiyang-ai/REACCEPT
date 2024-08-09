@Test
	@Verifies(value = "should fail validation if PersonName.degree is too long", method = "validate(java.lang.Object, org.springframework.validation.Errors, boolean, boolean)")
	public void validate_shouldFailValidationIfPersonNameDegreeIsTooLong() throws Exception {
		PersonName personName = new PersonName();
		personName.setGivenName("givenName");
		personName.setFamilyName("familyName");
		personName
		        .setDegree("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"); // 100 characters long
		Errors errors = new BindException(personName, "degree");
		new PersonNameValidator().validatePersonName(personName, errors, false, true);
		Assert.assertTrue(errors.hasFieldErrors("degree"));
	}