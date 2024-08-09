@Test
	@Verifies(value = "set this care setting to new order", method = "cloneForDiscontinuing(Order)")
	public void cloneForDiscontinuing_shouldSetThisCareSettingToNewOrder() {
		Order anOrder = new Order();
		CareSetting careSetting = new CareSetting();
		anOrder.setCareSetting(careSetting);
		
		Order orderThatCanDiscontinueTheOrder = anOrder.cloneForDiscontinuing();
		
		assertEquals(anOrder.getCareSetting(), orderThatCanDiscontinueTheOrder.getCareSetting());
		assertEquals(anOrder.getConcept(), orderThatCanDiscontinueTheOrder.getConcept());
		assertEquals(anOrder.getPatient(), orderThatCanDiscontinueTheOrder.getPatient());
		assertEquals("should set previous order to anOrder", anOrder, orderThatCanDiscontinueTheOrder.getPreviousOrder());
		assertEquals("should set new order action to new", Order.Action.DISCONTINUE, orderThatCanDiscontinueTheOrder.getAction());
	}