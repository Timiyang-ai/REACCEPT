	@Test
	public void validate_shouldFailIfTheObjectParameterIsNull() {
		
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("The parameter obj should not be null and must be of type" + Concept.class);
		validator.validate(null, errors);
	}