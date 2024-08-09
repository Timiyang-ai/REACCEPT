@Override
	public String getNewOrderNumber() {
		return ORDER_NUMBER_PREFIX + Context.getOrderService().getNextOrderNumberSeedSequenceValue();
	}