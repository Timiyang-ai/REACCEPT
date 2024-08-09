@Override
	public TestOrder cloneForDiscontinuing() {
		TestOrder newOrder = new TestOrder();
		newOrder.setCareSetting(this.getCareSetting());
		newOrder.setConcept(this.getConcept());
		newOrder.setAction(Action.DISCONTINUE);
		newOrder.setPreviousOrder(this);
		newOrder.setPatient(this.getPatient());
		newOrder.setOrderType(getOrderType());
		
		return newOrder;
	}