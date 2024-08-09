public void addOrder(Order order) {
		if (getMembers() == null)
			setMembers(new LinkedHashSet<Order>());
		order.setPatient(getPatient());
		getMembers().add(order);
	}