	@Test
	public void validate_shouldRequireMinOccurs() {
		
		attributeType.setMinOccurs(null);
		
		validator.validate(attributeType, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("minOccurs"));
		assertThat(errors.getFieldErrors("minOccurs").get(0).getCode(), is("error.null"));
	}