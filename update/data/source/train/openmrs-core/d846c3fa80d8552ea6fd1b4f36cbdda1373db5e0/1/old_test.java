@Test
	public void cloneForDiscontinuing_shouldSetAllTheRelevantFields() throws Exception {
		
		TestOrder anOrder = new TestOrder();
		anOrder.setPatient(new Patient());
		anOrder.setCareSetting(new CareSetting());
		anOrder.setConcept(new Concept());
		
		Order orderThatCanDiscontinueTheOrder = anOrder.cloneForDiscontinuing();
		
		assertEquals(anOrder.getPatient(), orderThatCanDiscontinueTheOrder.getPatient());
		
		assertEquals(anOrder.getConcept(), orderThatCanDiscontinueTheOrder.getConcept());
		
		assertEquals("should set previous order to anOrder", anOrder, orderThatCanDiscontinueTheOrder.getPreviousOrder());
		
		assertEquals("should set new order action to new", orderThatCanDiscontinueTheOrder.getAction(),
		    Order.Action.DISCONTINUE);
		
		assertEquals(anOrder.getCareSetting(), orderThatCanDiscontinueTheOrder.getCareSetting());
	}