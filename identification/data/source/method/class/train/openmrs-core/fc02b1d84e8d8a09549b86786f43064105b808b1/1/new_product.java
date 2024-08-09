public void addOrder(Order order) {
		if (order != null) {
			order.setEncounter(this);
			getOrders().add(order);
		}
	}