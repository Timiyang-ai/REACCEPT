@Test
	@Verifies(value = "should fail validation if description is null", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfDescriptionIsNull() {
		ImplementationId implementationId = new ImplementationId();
		implementationId.setImplementationId("IMPL_ID");
		implementationId.setPassphrase("PASSPHRASE");
		
		Errors errors = new BindException(implementationId, "implementationId");
		new ImplementationIdValidator().validate(implementationId, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("description"));
		Assert.assertFalse(errors.hasFieldErrors("implementationId"));
		Assert.assertFalse(errors.hasFieldErrors("passphrase"));
	}