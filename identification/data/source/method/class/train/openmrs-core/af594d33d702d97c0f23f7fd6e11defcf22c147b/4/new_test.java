	@Test
	public void cloneForDiscontinuing_shouldSetAllTheRelevantFields() {
		DrugOrder order = new DrugOrder();
		order.setPatient(new Patient());
		order.setCareSetting(new CareSetting());
		Drug drug = new Drug();
		drug.setConcept(new Concept());
		order.setDrug(drug);
		order.setOrderType(new OrderType());
		
		DrugOrder dcOrder = order.cloneForDiscontinuing();
		
		assertEquals(order.getDrug(), dcOrder.getDrug());
		
		assertEquals(order.getPatient(), dcOrder.getPatient());
		
		assertEquals(order.getConcept(), dcOrder.getConcept());
		
		assertEquals("should set previous order to anOrder", order, dcOrder.getPreviousOrder());
		
		assertEquals("should set new order action to new", dcOrder.getAction(), Order.Action.DISCONTINUE);
		
		assertEquals(order.getCareSetting(), dcOrder.getCareSetting());
		
		assertEquals(order.getOrderType(), dcOrder.getOrderType());
	}