@Override
	public Order discontinueOrder(Order orderToDiscontinue, Concept reasonCoded, Date discontinueDate, Provider orderer,
	        Encounter encounter) throws Exception {
		stopOrder(orderToDiscontinue, discontinueDate);
		Order newOrder = orderToDiscontinue.cloneForDiscontinuing();
		newOrder.setOrderReason(reasonCoded);
		newOrder.setOrderer(orderer);
		newOrder.setEncounter(encounter);
		if (discontinueDate == null) {
			discontinueDate = new Date();
		}
		newOrder.setDateActivated(discontinueDate);
		return saveOrderInternal(newOrder, null);
	}