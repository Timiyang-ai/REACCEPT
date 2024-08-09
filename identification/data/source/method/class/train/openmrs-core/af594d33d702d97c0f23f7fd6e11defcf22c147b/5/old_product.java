public Order cloneForDiscontinuing() {
		Order newOrder = new Order();
		newOrder.setCareSetting(this.getCareSetting());
		newOrder.setConcept(this.getConcept());
		newOrder.setAction(Action.DISCONTINUE);
		newOrder.setPreviousOrder(this);
		newOrder.setPatient(this.getPatient());
		newOrder.setOrderType(getOrderType());
		newOrder.setStartDate(this.getStartDate());
		
		return newOrder;
	}