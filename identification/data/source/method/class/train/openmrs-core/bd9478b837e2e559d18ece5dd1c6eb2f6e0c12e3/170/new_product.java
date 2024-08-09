public void discontinueOrder(Order order, String discontinueReason, Date discontinueDate) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_MANAGE_ORDERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_MANAGE_ORDERS);

		getOrderDAO().discontinueOrder(order, discontinueReason, discontinueDate);
	}