@Test
	@Verifies(value = "should pass validation if all fields are correct", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfAllFieldsAreCorrect() throws Exception {
		DrugOrder order = new DrugOrder();
		Encounter encounter = new Encounter();
		Patient patient = Context.getPatientService().getPatient(2);
		order.setConcept(Context.getConceptService().getConcept(88));
		order.setOrderer(Context.getProviderService().getProvider(1));
		order.setDosingType(DrugOrder.DosingType.FREE_TEXT);
		order.setInstructions("Instructions");
		order.setDosingInstructions("Test Instruction");
		order.setPatient(patient);
		encounter.setPatient(patient);
		order.setEncounter(encounter);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
		order.setStartDate(cal.getTime());
		order.setDateStopped(new Date());
		order.setAutoExpireDate(new Date());
		order.setOrderType(Context.getOrderService().getOrderTypeByName("Drug order"));
		order.setDrug(Context.getConceptService().getDrug(3));
		order.setCareSetting(Context.getOrderService().getCareSetting(1));
		order.setQuantity(2.00);
		order.setQuantityUnits(Context.getConceptService().getConcept(50));
		order.setNumRefills(10);
		
		Errors errors = new BindException(order, "order");
		new DrugOrderValidator().validate(order, errors);
		Assert.assertFalse(errors.hasErrors());
	}