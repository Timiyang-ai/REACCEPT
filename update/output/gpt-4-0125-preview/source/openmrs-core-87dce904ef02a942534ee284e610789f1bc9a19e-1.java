@Test
	public void cloneForDiscontinuing_shouldSetThisCareSettingToNewOrder() throws IllegalAccessException, InstantiationException {
		Order anOrder = new Order();
		CareSetting careSetting = new CareSetting();
		anOrder.setCareSetting(careSetting);
		
		Order orderThatCanDiscontinueTheOrder = anOrder.cloneForDiscontinuing();
		
		assertEquals(anOrder.getCareSetting(), orderThatCanDiscontinueTheOrder.getCareSetting());
		assertEquals(anOrder.getConcept(), orderThatCanDiscontinueTheOrder.getConcept());
		assertEquals(Order.Action.DISCONTINUE, orderThatCanDiscontinueTheOrder.getAction());
	}