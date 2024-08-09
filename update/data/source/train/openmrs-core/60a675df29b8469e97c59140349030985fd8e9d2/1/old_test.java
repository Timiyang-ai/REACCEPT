@Test
	public void getActiveOrders_shouldReturnActiveOrdersAsOfTheSpecifiedDate() throws Exception {
		Patient patient = Context.getPatientService().getPatient(2);
		Date asOfDate = Context.getDateTimeFormat().parse("02/12/2007 23:59:59");
		List<Order> orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(0, orders.size());
		
		asOfDate = Context.getDateFormat().parse("03/12/2007");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(1, orders.size());
		assertEquals(orderService.getOrder(2), orders.get(0));
		
		asOfDate = Context.getDateFormat().parse("10/12/2007");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(1, orders.size());
		assertEquals(orderService.getOrder(2), orders.get(0));
		
		asOfDate = Context.getDateTimeFormat().parse("10/12/2007 00:01:00");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(0, orders.size());
		
		asOfDate = Context.getDateFormat().parse("09/04/2008");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(3, orders.size());
		Order[] expectedOrders = { orderService.getOrder(222), orderService.getOrder(3), orderService.getOrder(4) };
		assertThat(orders, hasItems(expectedOrders));
		
		asOfDate = Context.getDateFormat().parse("25/09/2008");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(5, orders.size());
		Order[] expectedOrders1 = { orderService.getOrder(222), orderService.getOrder(3), orderService.getOrder(444),
		        orderService.getOrder(5), orderService.getOrder(6) };
		assertThat(orders, hasItems(expectedOrders1));
		
		asOfDate = Context.getDateTimeFormat().parse("26/09/2008 10:24:10");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(4, orders.size());
		Order[] expectedOrders2 = { orderService.getOrder(222), orderService.getOrder(3), orderService.getOrder(444),
		        orderService.getOrder(5) };
		assertThat(orders, hasItems(expectedOrders2));
		
		asOfDate = Context.getDateFormat().parse("20/11/2008");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(5, orders.size());
		Order[] expectedOrders3 = { orderService.getOrder(222), orderService.getOrder(3), orderService.getOrder(444),
		        orderService.getOrder(5), orderService.getOrder(7) };
		assertThat(orders, hasItems(expectedOrders3));
		
		asOfDate = Context.getDateFormat().parse("02/12/2008");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(6, orders.size());
		Order[] expectedOrders4 = { orderService.getOrder(222), orderService.getOrder(3), orderService.getOrder(444),
		        orderService.getOrder(5), orderService.getOrder(7), orderService.getOrder(9) };
		assertThat(orders, hasItems(expectedOrders4));
		
		asOfDate = Context.getDateFormat().parse("04/12/2008");
		orders = orderService.getActiveOrders(patient, null, null, asOfDate);
		assertEquals(5, orders.size());
		Order[] expectedOrders5 = { orderService.getOrder(222), orderService.getOrder(3), orderService.getOrder(444),
		        orderService.getOrder(5), orderService.getOrder(7) };
		assertThat(orders, hasItems(expectedOrders5));
	}