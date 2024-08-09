@Test
	@Verifies(value = "should set autoExpireDate if the discontinue date is in future", method = "discontinueOrder(Order order, Concept discontinueReason, Date discontinueDate)")
	public void discontinueOrder_shouldSetAutoExpireDateIfTheDiscontinueDateIsInFuture() throws Exception {
		String uuid = "921de0a3-05c4-444a-be03-e01b4c4b9142";
		Order order = Context.getOrderService().getOrderByUuid(uuid);
		Concept discontinudReason = Context.getConceptService().getConcept(1107);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 10);
		Date autoExpireDate = cal.getTime();
		Order updatedOrder = Context.getOrderService().discontinueOrder(order, discontinudReason, autoExpireDate);
		
		Assert.assertEquals(autoExpireDate, updatedOrder.getAutoExpireDate());
		
	}