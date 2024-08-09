public void addOrder(Order order) {
		order.setEncounter(this);
		if (orders == null)
			orders = new LinkedList<Order>();
		if (!orders.contains(order) && order != null)
			orders.add(order);
	}