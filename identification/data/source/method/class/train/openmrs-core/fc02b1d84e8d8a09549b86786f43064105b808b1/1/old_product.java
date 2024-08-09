public void addOrder(Order order) {
		if (orders == null)
			orders = new HashSet<Order>();
		if (order != null) {
			order.setEncounter(this);
			orders.add(order);
		}
	}