@Override
	public void purgeOrderType(OrderType orderType) {
		if (dao.isOrderTypeInUse(orderType)) {
			throw new APIException("Order.type.cannot.delete", (Object[]) null);
		}
		dao.purgeOrderType(orderType);
	}