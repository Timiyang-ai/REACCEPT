@Override
	public OrderFrequency saveOrderFrequency(OrderFrequency orderFrequency) throws APIException {
		if (orderFrequency.getOrderFrequencyId() != null) {
			if (dao.isOrderFrequencyInUse(orderFrequency)) {
				throw new CannotUpdateObjectInUseException("Order.frequency.cannot.edit");
			}
		}
		
		return dao.saveOrderFrequency(orderFrequency);
	}