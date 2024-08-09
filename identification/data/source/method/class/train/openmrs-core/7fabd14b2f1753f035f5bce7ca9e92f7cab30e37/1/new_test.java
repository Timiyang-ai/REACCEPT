@Test
	public void getActiveOrders_shouldReturnActiveOrdersAsOfTheSpecifiedDate() throws Exception {
		Patient patient = Context.getPatientService().getPatient(2);
		List<Order> orders = orderService.getAllOrdersByPatient(patient);
		assertEquals(12, orders.size());
		
		Date asOfDate = Context.getDateFormat().parse("10/12/2007");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(2, orders.size());
		assertFalse(orders.contains(orderService.getOrder(22)));//DC
		assertFalse(orders.contains(orderService.getOrder(44)));//DC
		assertFalse(orders.contains(orderService.getOrder(8)));//voided
		
		Order[] expectedOrders = { orderService.getOrder(9) };
		
		asOfDate = Context.getDateTimeFormat().parse("10/12/2007 00:01:00");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(1, orders.size());
		assertThat(orders, hasItems(expectedOrders));
		
		Order[] expectedOrders1 = { orderService.getOrder(3), orderService.getOrder(4), orderService.getOrder(222) };
		
		asOfDate = Context.getDateFormat().parse("10/04/2008");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(3, orders.size());
		assertThat(orders, hasItems(expectedOrders1));
		
		asOfDate = Context.getDateTimeFormat().parse("10/04/2008 00:01:00");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(2, orders.size());
		Order[] expectedOrders2 = { orderService.getOrder(222), orderService.getOrder(3) };
		assertThat(orders, hasItems(expectedOrders2));
		
		Order[] expectedOrders3 = { orderService.getOrder(222), orderService.getOrder(3), orderService.getOrder(444),
		        orderService.getOrder(5), orderService.getOrder(6) };
		asOfDate = Context.getDateTimeFormat().parse("26/09/2008 09:24:10");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(5, orders.size());
		assertThat(orders, hasItems(expectedOrders3));
		
		asOfDate = Context.getDateTimeFormat().parse("26/09/2008 09:25:10");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(4, orders.size());
		Order[] expectedOrders4 = { orderService.getOrder(222), orderService.getOrder(3), orderService.getOrder(444),
		        orderService.getOrder(5) };
		assertThat(orders, hasItems(expectedOrders4));
		
		asOfDate = Context.getDateFormat().parse("04/12/2008");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(5, orders.size());
		Order[] expectedOrders5 = { orderService.getOrder(222), orderService.getOrder(3), orderService.getOrder(444),
		        orderService.getOrder(5), orderService.getOrder(7) };
		assertThat(orders, hasItems(expectedOrders5));
		
		asOfDate = Context.getDateFormat().parse("06/12/2008");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(5, orders.size());
		assertThat(orders, hasItems(expectedOrders5));
	}