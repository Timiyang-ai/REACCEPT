	@Test
	public void addOrder_shouldAddOrderWithNullValues() {
		Encounter encounter = new Encounter();
		encounter.addOrder(new Order());
		assertEquals(1, encounter.getOrders().size());
	}