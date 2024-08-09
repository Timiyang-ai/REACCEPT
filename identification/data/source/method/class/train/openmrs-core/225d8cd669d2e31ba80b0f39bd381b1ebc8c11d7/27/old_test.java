	@Test
	public void validate_shouldFailIfAConceptIsNotSpecified() {
		Drug drug = new Drug();
		Errors errors = new BindException(drug, "drug");
		new ConceptDrugValidator().validate(drug, errors);
		Assert.assertTrue(errors.hasErrors());
	}