@Test
	@Verifies(value = "should fail if the name of the encounter role is not set", method = "validate(Object,Errors)")
	public void validate_shouldFailIfTheNameOfTheEncounterRoleIsNotSet() throws Exception {
		EncounterRole encounterRole = new EncounterRole();
		Errors errors = new BindException(encounterRole, "encounterRole");
		new EncounterRoleValidator().validate(encounterRole, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}