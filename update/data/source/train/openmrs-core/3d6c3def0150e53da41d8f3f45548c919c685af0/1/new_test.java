@Test
	public void validate_shouldBeValidIfIdentifierIsNotSet() throws Exception {
		//given
		provider.setIdentifier(null);
		provider.setName("bcj");
		
		//when
		providerValidator.validate(provider, errors);
		
		//then
		Assert.assertFalse(errors.hasErrors());
	}