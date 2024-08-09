public Order cloneForDiscontinuing() {
		Order newOrder = new Order();
		newOrder.setCareSetting(this.getCareSetting());
		newOrder.setConcept(this.getConcept());
		newOrder.setAction(Action.DISCONTINUE);
		newOrder.setPreviousOrder(this);
		
		return newOrder;
	}