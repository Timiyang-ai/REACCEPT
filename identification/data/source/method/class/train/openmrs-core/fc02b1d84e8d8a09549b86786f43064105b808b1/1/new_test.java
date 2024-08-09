@Test
	@Verifies(value = "should add order to non null initial order set", method = "addOrder(Order)")
	public void addOrder_shouldAddOrderToNonNullInitialOrderSet() throws Exception {
		Encounter encounter = new Encounter();
		Set<Order> orderSet = new HashSet<Order>();
		orderSet.add(new Order(1));
		
		encounter.setOrders(orderSet);
		
		encounter.addOrder(new Order(2));
		assertEquals(2, encounter.getOrders().size());
	}