@Test
	@Verifies(value = "should asign order number for new order", method = "saveOrder(Order)")
	public void saveOrder_shouldAssignOrderNumberForNewOrder() throws Exception {
		Order order = new Order();
		order.setOrderType(Context.getOrderService().getOrderType(2));
		order.setConcept(Context.getConceptService().getConcept(23));
		order.setPatient(Context.getPatientService().getPatient(6));
		
		String nextAvaliableOrderNumber = Context.getOrderService().getNewOrderNumber();
		
		Context.getOrderService().saveOrder(order);
		
		Assert.assertNotNull(order.getOrderId());
		Assert.assertEquals(nextAvaliableOrderNumber, order.getOrderNumber());
		Assert.assertTrue(order.isLatestVersion());
		Assert.assertTrue(order.getOrderVersion() == 1);
	}