@Test
	@Verifies(value = "should reject a duplicate identifier for a new provider", method = "validate(Object,Errors)")
	public void validate_shouldRejectADuplicateIdentifierForANewProvider() throws Exception {
		Provider duplicateProvider = providerService.getProvider(1);
		Assert.assertFalse(duplicateProvider.isRetired());
		
		Provider provider = new Provider();
		provider.setIdentifier(duplicateProvider.getIdentifier());
		provider.setName("name");
		
		providerValidator.validate(provider, errors);
		Assert.assertTrue(errors.hasFieldErrors("identifier"));
	}