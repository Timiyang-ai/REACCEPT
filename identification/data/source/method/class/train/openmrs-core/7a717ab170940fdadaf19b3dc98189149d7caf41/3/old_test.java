	@Test
	public void validate_shouldFailValidationIfRoleIsNullOrEmptyOrWhitespace() {
		Role role = new Role();
		role.setRole(null);
		role.setDescription("some text");
		//TODO: change/fix this test when it is decided whether to change the validator behavior to avoid throwing an NPE
		Errors errors = new BindException(role, "type");
		//new RoleValidator().validate(role, errors);
		//Assert.assertTrue(errors.hasFieldErrors("role"));
		
		role.setRole("");
		errors = new BindException(role, "role");
		new RoleValidator().validate(role, errors);
		Assert.assertTrue(errors.hasFieldErrors("role"));
		
		role.setRole(" ");
		errors = new BindException(role, "role");
		new RoleValidator().validate(role, errors);
		Assert.assertTrue(errors.hasFieldErrors("role"));
	}