@Override
	public void purgeOrderFrequency(OrderFrequency orderFrequency) {
		
		if (dao.isOrderFrequencyInUse(orderFrequency)) {
			throw new APIException("Order.frequency.cannot.delete", (Object[]) null);
		}
		
		dao.purgeOrderFrequency(orderFrequency);
	}