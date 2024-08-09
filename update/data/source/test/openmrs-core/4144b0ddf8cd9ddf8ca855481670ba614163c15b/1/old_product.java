@Override
	public OrderFrequency saveOrderFrequency(OrderFrequency orderFrequency) throws APIException {
		
		if (orderFrequency.getOrderFrequencyId() != null) {
			if (dao.isOrderFrequencyInUse(orderFrequency)) {
				throw new APIException("This order frequency cannot be edited because it is already in use");
			}
		}
		
		ValidateUtil.validate(orderFrequency);
		return dao.saveOrderFrequency(orderFrequency);
	}