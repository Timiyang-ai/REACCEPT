@Test
	@Verifies(value = "should fail validation if description is null or empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfDescriptionIsNullOrEmptyOrWhitespace() throws Exception {
		Role role = new Role();
		role.setRole("Bowling race car driver");
		role.setDescription(null);
		
		Errors errors = new BindException(role, "type");
		new RoleValidator().validate(role, errors);
		Assert.assertTrue(errors.hasFieldErrors("description"));
		
		role.setDescription("");
		errors = new BindException(role, "role");
		new RoleValidator().validate(role, errors);
		Assert.assertTrue(errors.hasFieldErrors("description"));
		
		role.setDescription(" ");
		errors = new BindException(role, "role");
		new RoleValidator().validate(role, errors);
		Assert.assertTrue(errors.hasFieldErrors("description"));
	}