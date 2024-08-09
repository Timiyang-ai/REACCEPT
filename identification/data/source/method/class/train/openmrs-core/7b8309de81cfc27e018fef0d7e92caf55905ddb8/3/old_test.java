@Test
	@Verifies(value = "should fail validation if startDate after discontinuedDate", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfStartDateAfterDiscontinuedDate() throws Exception {
		Order order = new Order();
		;
		order.setConcept(Context.getConceptService().getConcept(88));
		order.setPatient(Context.getPatientService().getPatient(2));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
		order.setStartDate(new Date());
		order.setDiscontinuedDate(cal.getTime());
		
		Errors errors = new BindException(order, "order");
		new OrderValidator().validate(order, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("startDate"));
		Assert.assertTrue(errors.hasFieldErrors("discontinuedDate"));
	}