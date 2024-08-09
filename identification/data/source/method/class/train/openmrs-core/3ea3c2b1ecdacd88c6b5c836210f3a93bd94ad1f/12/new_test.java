	@Test
	public void validatePersonName_shouldNotValidateAgainstRegexForBlankNames() {
		
		personName.setGivenName("given");
		personName.setFamilyName("family");
		personName.setMiddleName("");
		personName.setFamilyName2("");
		
		validator.validatePersonName(personName, errors, false, true);
		
		Assert.assertFalse(errors.hasErrors());
	}