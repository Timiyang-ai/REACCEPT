@Test
	public void validate_shouldFailIfTheDrugObjectIsNull() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("The parameter obj should not be null and must be of type" + Drug.class);
		new DrugValidator().validate(null, new BindException(new Drug(), "drug"));
	}