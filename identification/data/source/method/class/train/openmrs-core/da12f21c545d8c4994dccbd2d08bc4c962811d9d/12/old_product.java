@Override
	public void purgeOrderType(OrderType orderType) {
		if (dao.isOrderTypeInUse(orderType)) {
			throw new APIException("This order type cannot be deleted because it is already in use");
		}
		dao.purgeOrderType(orderType);
	}