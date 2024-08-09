public void addOrder(Order order, Integer position) {
		if (order == null || getOrders().contains(order)) {
			return;
		}
                
                order.setOrderGroup(this);  
                 
		Integer listIndex = findListIndexForGivenPosition(position);
		getOrders().add(listIndex, order);
		if (order.getSortWeight() == null) {
			order.setSortWeight(findSortWeight(listIndex));
		}
	}