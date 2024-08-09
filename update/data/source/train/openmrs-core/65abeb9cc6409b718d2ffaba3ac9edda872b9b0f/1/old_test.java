@Test
	public void validate_shouldNotRequireAllFieldsForADiscontinuationOrder() throws Exception {
		DrugOrder orderToDiscontinue = (DrugOrder) Context.getOrderService().getOrder(111);
		assertTrue(OrderUtilTest.isActiveOrder(orderToDiscontinue, null));
		DrugOrder discontinuationOrder = new DrugOrder();
		discontinuationOrder.setCareSetting(orderToDiscontinue.getCareSetting());
		discontinuationOrder.setConcept(orderToDiscontinue.getConcept());
		discontinuationOrder.setAction(Order.Action.DISCONTINUE);
		discontinuationOrder.setPreviousOrder(orderToDiscontinue);
		discontinuationOrder.setPatient(orderToDiscontinue.getPatient());
		discontinuationOrder.setDrug(orderToDiscontinue.getDrug());
		discontinuationOrder.setOrderType(orderToDiscontinue.getOrderType());
		discontinuationOrder.setOrderer(Context.getProviderService().getProvider(1));
		discontinuationOrder.setEncounter(Context.getEncounterService().getEncounter(3));
		
		Errors errors = new BindException(discontinuationOrder, "order");
		new DrugOrderValidator().validate(discontinuationOrder, errors);
		Assert.assertFalse(errors.hasErrors());
	}