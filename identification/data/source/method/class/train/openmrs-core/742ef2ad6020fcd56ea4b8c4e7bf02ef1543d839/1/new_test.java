@Test
	@Verifies(value = "should fail if the name of the encounter role is null empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldFailIfTheNameOfTheEncounterRoleIsNullEmptyOrWhitespace() throws Exception {
		
		EncounterRole encounterRoleNo1 = new EncounterRole();
		encounterRoleNo1.setName(null);
		Errors errorsNo1 = new BindException(encounterRoleNo1, "encounterRole");
		new EncounterRoleValidator().validate(encounterRoleNo1, errorsNo1);
		Assert.assertTrue(errorsNo1.hasFieldErrors("name"));
		
		EncounterRole encounterRoleNo2 = new EncounterRole();
		encounterRoleNo2.setName("");
		Errors errorsNo2 = new BindException(encounterRoleNo2, "encounterRole");
		new EncounterRoleValidator().validate(encounterRoleNo2, errorsNo2);
		Assert.assertTrue(errorsNo2.hasFieldErrors("name"));
		
		EncounterRole encounterRoleNo3 = new EncounterRole();
		encounterRoleNo3.setName("  ");
		Errors errorsNo3 = new BindException(encounterRoleNo3, "encounterRole");
		new EncounterRoleValidator().validate(encounterRoleNo3, errorsNo3);
		Assert.assertTrue(errorsNo3.hasFieldErrors("name"));
	}