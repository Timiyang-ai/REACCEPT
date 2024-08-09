@Test
	public void validate_shouldBeInvalidIfBothPersonAndNameAreSet() throws Exception {
		//given
		provider.setIdentifier("id");
		provider.setPerson(new Person(1));
		provider.setName("1");
		
		//when
		providerValidator.validate(provider, errors);
		
		//then
		Assert.assertTrue(errors.hasErrors());
		Assert.assertTrue(errors.hasFieldErrors("name"));
		Assert.assertEquals("Provider.error.personOrName.required", errors.getFieldError("name").getCode());
	}