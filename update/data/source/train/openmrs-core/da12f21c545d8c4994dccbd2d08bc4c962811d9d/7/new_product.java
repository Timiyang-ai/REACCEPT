@Override
	public OrderFrequency saveOrderFrequency(OrderFrequency orderFrequency) throws APIException {
		
		if (orderFrequency.getOrderFrequencyId() != null && dao.isOrderFrequencyInUse(orderFrequency)) {
				throw new APIException("This order frequency cannot be edited because it is already in use");
		}
		
		return dao.saveOrderFrequency(orderFrequency);
	}