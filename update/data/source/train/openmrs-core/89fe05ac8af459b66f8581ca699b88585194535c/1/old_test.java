@Test
	@Verifies(value = "should fail validation if unlocalized name is null or empty", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfUnlocalizedNameIsNullOrEmpty() throws Exception {
		Location location = new Location();
		location.setDescription("desc");
		
		Errors errors = new BindException(location, "location");
		new LocationValidator().validate(location, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("localizedName.unlocalizedValue"));
		Assert.assertFalse(errors.hasFieldErrors("description"));
	}