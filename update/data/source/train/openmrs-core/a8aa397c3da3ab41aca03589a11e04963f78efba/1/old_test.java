@Test
	@Verifies(value = "should pass validation if all fields are correct", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfAllFieldsAreCorrect() throws Exception {
		Order order = new Order();
		order.setConcept(Context.getConceptService().getConcept(88));
		order.setPatient(Context.getPatientService().getPatient(2));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
		order.setStartDate(cal.getTime());
		order.setAutoExpireDate(new Date());
		order.setDateSigned(new Date());
		order.setSignedBy(Context.getUserService().getUser(1));
		order.setDateActivated(new Date());
		order.setActivatedBy(Context.getUserService().getUser(1));
		
		Errors errors = new BindException(order, "order");
		new OrderValidator().validate(order, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}