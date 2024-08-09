@Test
	@Verifies(value = "should discontinue previousOrder if it is not already discontinued", method = "saveOrder(Order)")
	public void saveOrder_shouldDiscontinuePreviousOrderIfItIsNotAlreadyDiscontinued() throws Exception {
		//We are trying to discontinue order id 111 in standardTestDataset.xml
		DrugOrder order = new DrugOrder();
		order.setAction(Order.Action.DISCONTINUE);
		order.setOrderReasonNonCoded("Discontinue this");
		order.setDrug(conceptService.getDrug(3));
		order.setEncounter(encounterService.getEncounter(5));
		order.setPatient(Context.getPatientService().getPatient(7));
		order.setOrderer(Context.getProviderService().getProvider(1));
		order.setCareSetting(orderService.getCareSetting(1));
		order.setEncounter(encounterService.getEncounter(3));
		order.setOrderType(orderService.getOrderType(1));
		order.setStartDate(new Date());
		order.setDosingType(DrugOrder.DosingType.SIMPLE);
		order.setDose(500.0);
		order.setDoseUnits(conceptService.getConcept(50));
		order.setFrequency(orderService.getOrderFrequency(1));
		order.setRoute(conceptService.getConcept(22));
		order.setNumRefills(10);
		order.setQuantity(20.0);
		order.setQuantityUnits(conceptService.getConcept(51));
		Order previousOrder = orderService.getOrder(111);
		assertTrue(OrderUtilTest.isActiveOrder(previousOrder, null));
		order.setPreviousOrder(previousOrder);
		
		orderService.saveOrder(order, null);
		Assert.assertEquals(order.getStartDate(), order.getAutoExpireDate());
		Assert.assertNotNull("previous order should be discontinued", previousOrder.getDateStopped());
	}