@Override
	@Transactional(readOnly = true)
	public List<Order> getOrderHistoryByOrderNumber(String orderNumber) {
		List<Order> orders = new ArrayList<Order>();
		Order order = dao.getOrderByOrderNumber(orderNumber);
		while (order != null) {
			orders.add(order);
			order = order.getPreviousOrder();
		}
		return orders;
	}