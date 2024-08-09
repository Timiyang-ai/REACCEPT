@Test
	public void cloneForDiscontinuing_shouldSetAllTheRelevantFields() throws Exception {
		
		TestOrder anOrder = new TestOrder();
		anOrder.setPatient(new Patient());
		anOrder.setCareSetting(new CareSetting());
		anOrder.setConcept(new Concept());
		anOrder.setUuid(UUID.randomUUID().toString());
		
		Order orderThatCanDiscontinueTheOrder = anOrder.cloneForDiscontinuing();
		
		assertEquals(anOrder.getClass(), orderThatCanDiscontinueTheOrder.getClass());
		
		assertEquals(anOrder.getPatient(), orderThatCanDiscontinueTheOrder.getPatient());
		
		assertEquals(anOrder.getConcept(), orderThatCanDiscontinueTheOrder.getConcept());
		
		assertEquals("should set previous order to anOrder", orderThatCanDiscontinueTheOrder.getPreviousOrder(), anOrder);
		
		assertEquals("should set new order action to new", orderThatCanDiscontinueTheOrder.getAction(),
		    Order.Action.DISCONTINUE);
		
		assertEquals(anOrder.getCareSetting(), orderThatCanDiscontinueTheOrder.getCareSetting());
	}