@Test
	@Verifies(value = "should return the next available order number", method = "handle(Order,User,Date,String)")
	public void handle_shouldReturnTheNextAvailableOrderNumber() throws Exception {
		//call the method twice and ensure that the sequence works as expected
		Order order1 = new Order();
		new OrderSaveHandler().handle(order1, null, null, null);
		String orderNumber1 = order1.getOrderNumber();
		Integer nextNumber = Integer.valueOf(orderNumber1.substring(OpenmrsConstants.ORDER_NUMBER_DEFAULT_PREFIX.length())) + 1;
		String expectedOrderNumber = OpenmrsConstants.ORDER_NUMBER_DEFAULT_PREFIX.concat(nextNumber.toString());
		
		Order order2 = new Order();
		new OrderSaveHandler().handle(order2, null, null, null);
		Assert.assertEquals(expectedOrderNumber, order2.getOrderNumber());
	}