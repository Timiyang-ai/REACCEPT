public Order unvoidOrder(Order order) throws APIException {
		Order previousOrder = order.getPreviousOrder();
		if (previousOrder != null && isDiscontinueOrReviseOrder(order)) {
			if (!previousOrder.isActive()) {
				final String action = DISCONTINUE == order.getAction() ? "discontinuation" : "revision";
				throw new APIException("Order.action.cannot.unvoid", new Object[] { action });
			}
			stopOrder(previousOrder, aMomentBefore(order.getDateActivated()), false);
		}
		
		return saveOrderInternal(order, null);
	}