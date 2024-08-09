public void handle(Order order, User creator, Date dateCreated, String other) {
		if (order.getPatient() == null && order.getEncounter() != null) {
			order.setPatient(order.getEncounter().getPatient());
		}
		if (order.getOrderNumber() == null) {
			order.setOrderNumber(Context.getOrderService().getNewOrderNumber());
		}
	}