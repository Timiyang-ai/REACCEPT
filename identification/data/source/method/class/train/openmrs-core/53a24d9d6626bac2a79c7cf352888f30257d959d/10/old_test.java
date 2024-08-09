	@Test
	public void getActiveOrders_shouldReturnAllActiveOrdersForTheSpecifiedPatient() {
		Patient patient = Context.getPatientService().getPatient(2);
		List<Order> orders = orderService.getActiveOrders(patient, null, null, null);
		assertEquals(5, orders.size());
		Order[] expectedOrders = { orderService.getOrder(222), orderService.getOrder(3), orderService.getOrder(444),
		        orderService.getOrder(5), orderService.getOrder(7) };
		assertThat(orders, hasItems(expectedOrders));
		
		assertTrue(OrderUtilTest.isActiveOrder(orders.get(0), null));
		assertTrue(OrderUtilTest.isActiveOrder(orders.get(1), null));
		assertTrue(OrderUtilTest.isActiveOrder(orders.get(2), null));
		assertTrue(OrderUtilTest.isActiveOrder(orders.get(3), null));
		assertTrue(OrderUtilTest.isActiveOrder(orders.get(4), null));
	}