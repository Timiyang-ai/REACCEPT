public void addOrder(Order order) {
		order.setEncounter(this);
		if (orders == null)
			orders = new HashSet<Order>();
		if (!orders.contains(order) && order != null)
			orders.add(order);
	}