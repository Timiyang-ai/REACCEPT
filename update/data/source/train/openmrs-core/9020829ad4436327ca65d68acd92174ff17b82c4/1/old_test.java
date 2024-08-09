@Test
	@Verifies(value = "should fail if required fields are empty", method = "validate(Object,Errors)")
	public void validate_shouldFailIfRequiredFieldsAreEmpty() throws Exception {
		executeDataSet(PERSON_ADDRESS_VALIDATOR_DATASET_PACKAGE_PATH);
		PersonAddress personAddress = new PersonAddress();
		
		Errors errors = new BindException(personAddress, "personAddress");
		validator.validate(personAddress, errors);
		Assert.assertEquals(true, errors.hasErrors());
	}