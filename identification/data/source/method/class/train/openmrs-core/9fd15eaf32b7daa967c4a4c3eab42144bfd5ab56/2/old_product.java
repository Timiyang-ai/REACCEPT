@Override
	public Order discontinueOrder(Order orderToDiscontinue, String reasonNonCoded, Date discontinueDate, Provider orderer,
	        Encounter encounter) throws Exception {
		stopOrder(orderToDiscontinue, discontinueDate);
		Order newOrder = orderToDiscontinue.cloneForDiscontinuing();
		newOrder.setOrderReasonNonCoded(reasonNonCoded);
		newOrder.setOrderer(orderer);
		newOrder.setEncounter(encounter);
		if (discontinueDate == null) {
			discontinueDate = new Date();
		}
		newOrder.setStartDate(discontinueDate);
		return saveOrderInternal(newOrder, null);
	}