@Override
	public void purgeOrderType(OrderType orderType) {
		if (dao.isOrderTypeInUse(orderType)) {
			throw CannotDeleteOrderPropertyInUseException.withProperty("type");
		}
		dao.purgeOrderType(orderType);
	}