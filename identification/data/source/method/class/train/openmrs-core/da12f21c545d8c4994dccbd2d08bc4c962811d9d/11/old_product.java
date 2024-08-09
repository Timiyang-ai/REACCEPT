@Override
	public void purgeOrderFrequency(OrderFrequency orderFrequency) {
		
		if (dao.isOrderFrequencyInUse(orderFrequency)) {
			throw new APIException("This order frequency cannot be deleted because it is already in use");
		}
		
		dao.purgeOrderFrequency(orderFrequency);
	}