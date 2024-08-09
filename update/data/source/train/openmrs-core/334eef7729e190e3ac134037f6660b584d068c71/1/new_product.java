@Override
	public OrderGroup saveOrderGroup(OrderGroup group) throws APIException {
		ValidateUtil.validate(group);
		return dao.saveOrderGroup(group);
	}