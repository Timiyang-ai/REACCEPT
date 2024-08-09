@Test
	public void validate_shouldBeInvalidIfIdentifierIsNotSet() throws Exception {
		//given
		provider.setIdentifier(null);
		
		//when
		providerValidator.validate(provider, errors);
		
		//then
		Assert.assertTrue(errors.hasFieldErrors("identifier"));
		Assert.assertEquals("Provider.error.identifier.required", errors.getFieldError("identifier").getCode());
	}