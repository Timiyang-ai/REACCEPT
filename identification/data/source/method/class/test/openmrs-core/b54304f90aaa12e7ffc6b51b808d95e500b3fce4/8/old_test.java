	@Test
	public void removeOrder_shouldRemoveOrderFromEncounter() {
		Encounter encounter = new Encounter();
		Order order = new Order(1);
		encounter.addOrder(order);
		assertEquals(1, encounter.getOrders().size());
		
		encounter.removeOrder(order);
		assertEquals(0, encounter.getOrders().size());
	}