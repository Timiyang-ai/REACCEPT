@Override
	public DrugOrder cloneForDiscontinuing() {
		DrugOrder newOrder = new DrugOrder();
		newOrder.setCareSetting(this.getCareSetting());
		newOrder.setConcept(this.getConcept());
		newOrder.setAction(Action.DISCONTINUE);
		newOrder.setPreviousOrder(this);
		newOrder.setPatient(this.getPatient());
		newOrder.setDrug(this.getDrug());
		newOrder.setOrderType(this.getOrderType());
		return newOrder;
	}