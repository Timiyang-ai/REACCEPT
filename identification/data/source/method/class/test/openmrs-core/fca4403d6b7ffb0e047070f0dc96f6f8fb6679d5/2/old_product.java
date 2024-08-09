@Override
	public OrderFrequency saveOrderFrequency(OrderFrequency orderFrequency) throws APIException {
		if (orderFrequency.getOrderFrequencyId() != null) {
			if (dao.isOrderFrequencyInUse(orderFrequency)) {
				throw CannotEditOrderPropertyInUseException.withProperty("frequency");
			}
		}
		
		return dao.saveOrderFrequency(orderFrequency);
	}