public void handle(Order order, User creator, Date dateCreated, String other) {
		if (order.getPatient() == null && order.getEncounter() != null) {
			order.setPatient(order.getEncounter().getPatient());
		}
		
		if (order.getOrderType() == null) {
			OrderType orderType = Context.getOrderService().getOrderTypeByConcept(order.getConcept());
			if (orderType == null) {
				throw new APIException("No order type matches the concept class");
			}
			order.setOrderType(orderType);
		}
	}