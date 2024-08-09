@Test
	@Verifies(value = "should pass validation if description is null", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfDescriptionIsNull() {
		ImplementationId implementationId = new ImplementationId();
		implementationId.setImplementationId("IMPL_ID");
		implementationId.setPassphrase("PASSPHRASE");
		
		Errors errors = new BindException(implementationId, "implementationId");
		new ImplementationIdValidator().validate(implementationId, errors);
		
		Assert.assertFalse(errors.hasFieldErrors("description"));
		Assert.assertFalse(errors.hasFieldErrors("implementationId"));
		Assert.assertFalse(errors.hasFieldErrors("passphrase"));
	}