@Test
	public void validate_shouldBeInvalidIfPersonIsNotSet() throws Exception {
		//given
		provider.setIdentifier("id");
		provider.setPerson(null);
		
		//when
		providerValidator.validate(provider, errors);
		
		//then
		Assert.assertTrue(errors.hasErrors());
		Assert.assertTrue(errors.hasFieldErrors("name"));
		Assert.assertTrue(errors.hasFieldErrors("person"));
		Assert.assertEquals("Provider.error.personOrName.required", errors.getFieldError("name").getCode());
		Assert.assertEquals("Provider.error.personOrName.required", errors.getFieldError("person").getCode());
	}