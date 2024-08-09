@Test
	public void validate_shouldFailValidationIfEmailAsUsernameEnabledAndEmailInvalid() throws Exception {
		User user = new User();
		user.setUsername("test@example.com");
		
		AdministrationService as = Context.getAdministrationService();
		as.saveGlobalProperty(new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_USER_REQUIRE_EMAIL_AS_USERNAME, "true"));
		
		Errors errors = new BindException(user, "user");
		validator.validate(user, errors);
		
		Assert.assertFalse(errors.hasFieldErrors("username"));
	}