@Test
	public void validate_shouldNeverHaveBothPersonAndNameSet() throws Exception {
		//given
		provider.setIdentifier("id");
		provider.setPerson(new Person(1));
		provider.setName("1");
		
		//when
		providerValidator.validate(provider, errors);
		
		//then
		Assert.assertFalse(errors.hasErrors());
		Assert.assertFalse(errors.hasFieldErrors("name"));
	}