public Order cloneForDiscontinuing() {
		Order newOrder = new Order();
		newOrder.setCareSetting(getCareSetting());
		newOrder.setConcept(getConcept());
		newOrder.setAction(Action.DISCONTINUE);
		newOrder.setPreviousOrder(this);
		newOrder.setPatient(getPatient());
		newOrder.setOrderType(getOrderType());
		
		return newOrder;
	}