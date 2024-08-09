@Test(expected = IllegalArgumentException.class)
	@Verifies(value = "should fail if the drug object is null", method = "validate(Object,Errors)")
	public void validate_shouldFailIfTheDrugObjectIsNull() {
		Errors errors = new BindException(new Drug(), "drug");
		new DrugValidator().validate(null, errors);
	}