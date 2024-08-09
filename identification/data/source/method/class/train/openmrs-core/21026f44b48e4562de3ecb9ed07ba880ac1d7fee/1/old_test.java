@Test
	public void cloneForDiscontinuing_shouldSetAllTheRelevantFields() throws Exception {
		
		TestOrder anOrder = new TestOrder();
		anOrder.setUuid(UUID.randomUUID().toString());
		
		Order orderThatCanDiscontinueTheOrder = anOrder.cloneForDiscontinuing();
		
		assertEquals(anOrder.getClass(), anOrder.cloneForDiscontinuing().getClass());
		
		assertEquals("should set previous order to anOrder", orderThatCanDiscontinueTheOrder.getPreviousOrder(), anOrder);
		
		assertEquals("should set new order action to new", orderThatCanDiscontinueTheOrder.getAction(),
		    Order.Action.DISCONTINUE);
		
		assertEquals(anOrder.getCareSetting(), orderThatCanDiscontinueTheOrder.getCareSetting());
	}