public void voidOrder(Order order, String voidReason) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_MANAGE_ORDERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_MANAGE_ORDERS);

		getOrderDAO().voidOrder(order, voidReason);
	}