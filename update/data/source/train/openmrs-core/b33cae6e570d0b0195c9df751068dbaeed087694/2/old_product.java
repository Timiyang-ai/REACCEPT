@Override
	public void purgeOrderFrequency(OrderFrequency orderFrequency) {
		
		if (dao.isOrderFrequencyInUse(orderFrequency)) {
			throw new APIException("Order.frequency.cannot.delete");
		}
		
		dao.purgeOrderFrequency(orderFrequency);
	}