	@Test
	public void validate_shouldFailValidationIfNameIsNullOrEmpty() {
		Location location = new Location();
		location.setDescription("desc");
		
		Errors errors = new BindException(location, "location");
		new LocationValidator().validate(location, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("name"));
		Assert.assertFalse(errors.hasFieldErrors("description"));
	}