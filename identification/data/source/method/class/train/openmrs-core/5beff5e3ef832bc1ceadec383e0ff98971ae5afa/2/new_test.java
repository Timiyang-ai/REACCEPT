	@Test
	public void validate_shouldFailValidationIfAutoExpireDateIsNotSetAndDurationUnitsIsNotMappedToSNOMEDCTDuration()
	        {
		DrugOrder drugOrder = createValidDrugOrder();
		drugOrder.setDuration(30);
		Concept unMappedDurationUnits = new Concept();
		drugOrder.setDurationUnits(unMappedDurationUnits);
		drugOrder.setAutoExpireDate(null);
		Errors errors = new BindException(drugOrder, "drugOrder");
		
		new SimpleDosingInstructions().validate(drugOrder, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("durationUnits"));
		Assert.assertEquals("DrugOrder.error.durationUnitsNotMappedToSnomedCtDurationCode", errors.getFieldError(
		    "durationUnits").getCode());
	}