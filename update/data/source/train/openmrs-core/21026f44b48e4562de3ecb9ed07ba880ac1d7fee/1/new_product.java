public Order cloneForDiscontinuing() throws IllegalAccessException, InstantiationException {
		Order newOrder = this.getClass().newInstance();
		newOrder.setCareSetting(this.getCareSetting());
		newOrder.setConcept(this.getConcept());
		newOrder.setAction(Action.DISCONTINUE);
		newOrder.setPreviousOrder(this);
		newOrder.setPatient(this.getPatient());
		
		return newOrder;
	}