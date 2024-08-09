	@Test
	public void validate_shouldFailIfTheConceptMapTypeNameIsADuplicate() {
		ConceptMapType mapType = new ConceptMapType();
		mapType.setName("is a");
		Errors errors = new BindException(mapType, "mapType");
		new ConceptMapTypeValidator().validate(mapType, errors);
		Assert.assertEquals(true, errors.hasFieldErrors("name"));
	}