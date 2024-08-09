public void addOrder(Order order) {
		if (getMembers() == null)
			setMembers(new LinkedHashSet<Order>());
		getMembers().add(order);
	}