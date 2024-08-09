	@Test
	public void validate_shouldFailValidationIfPersonIdIsNull() {
		Obs obs = new Obs();
		obs.setConcept(Context.getConceptService().getConcept(5089));
		obs.setObsDatetime(new Date());
		obs.setValueNumeric(1.0);
		
		Errors errors = new BindException(obs, "obs");
		obsValidator.validate(obs, errors);
		
		assertTrue(errors.hasFieldErrors("person"));
		assertFalse(errors.hasFieldErrors("concept"));
		assertFalse(errors.hasFieldErrors("obsDatetime"));
		assertFalse(errors.hasFieldErrors("valueNumeric"));
	}