@Override
	public void purgeOrderFrequency(OrderFrequency orderFrequency) {
		
		if (dao.isOrderFrequencyInUse(orderFrequency)) {
			throw CannotDeleteOrderPropertyInUseException.withProperty("frequency");
		}
		
		dao.purgeOrderFrequency(orderFrequency);
	}