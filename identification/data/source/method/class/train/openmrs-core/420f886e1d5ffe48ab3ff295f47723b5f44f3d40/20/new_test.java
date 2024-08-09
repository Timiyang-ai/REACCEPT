	@Test
	public void validate_shouldFailValidationIfPrivilegeIsNullOrEmptyOrWhitespace() {
		Privilege priv = new Privilege();
		priv.setPrivilege(null);
		priv.setDescription("some text");
		
		Errors errors = new BindException(priv, "priv");
		new PrivilegeValidator().validate(priv, errors);
		Assert.assertTrue(errors.hasFieldErrors("privilege"));
		
		priv.setPrivilege("");
		errors = new BindException(priv, "priv");
		new PrivilegeValidator().validate(priv, errors);
		Assert.assertTrue(errors.hasFieldErrors("privilege"));
		
		priv.setPrivilege(" ");
		errors = new BindException(priv, "priv");
		new PrivilegeValidator().validate(priv, errors);
		Assert.assertTrue(errors.hasFieldErrors("privilege"));
	}