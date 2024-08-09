public void discontinueOrder(Order order, String reason) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_EDIT_ORDERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_EDIT_ORDERS);
		getOrderDAO().discontinueOrder(order, reason);
	}