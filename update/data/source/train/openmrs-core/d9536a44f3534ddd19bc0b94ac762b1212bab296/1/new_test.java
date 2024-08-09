@Test
	@Verifies(value = "should pass validation if description is null or empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfDescriptionIsNullOrEmptyOrWhitespace() throws Exception {
		Role role = new Role();
		role.setRole("Bowling race car driver");
		role.setDescription(null);
		
		Errors errors = new BindException(role, "type");
		new RoleValidator().validate(role, errors);
		Assert.assertFalse(errors.hasFieldErrors("description"));
		
		role.setDescription("");
		errors = new BindException(role, "role");
		new RoleValidator().validate(role, errors);
		Assert.assertFalse(errors.hasFieldErrors("description"));
		
		role.setDescription(" ");
		errors = new BindException(role, "role");
		new RoleValidator().validate(role, errors);
		Assert.assertFalse(errors.hasFieldErrors("description"));
	}