@Override
	public Order saveOrder(Order order) throws APIException {
		if (dao.isActivatedInDatabase(order))
			throw new APIException("Cannot modify an activated order");
		
		return saveOrderWithLesserValidation(order);
	}