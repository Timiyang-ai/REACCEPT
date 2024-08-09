public Order unvoidOrder(Order order) throws APIException {
		Order previousOrder = order.getPreviousOrder();
		if (previousOrder != null && isDiscontinueOrReviseOrder(order)) {
			if (!previousOrder.isActive()) {
				final String action = DISCONTINUE == order.getAction() ? "discontinuation" : "revision";
				throw new APIException("Cannot unvoid a " + action + " order if the previous order is no longer active");
			}
			stopOrder(previousOrder, aMomentBefore(order.getDateActivated()));
		}
		
		return saveOrderInternal(order, null);
	}