public void addOrder(Order order, Integer position) {
		if (order == null || getOrders().contains(order)) {
			return;
		}
		Integer listIndex = findListIndexForGivenPosition(position);
		getOrders().add(listIndex, order);
		if (order.getSortWeight() == null) {
			order.setSortWeight(findSortWeight(listIndex));
		}
	}