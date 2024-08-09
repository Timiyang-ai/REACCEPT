public List<OrderType> getOrderTypes() throws APIException {
		return context.getDAOContext().getOrderDAO().getOrderTypes();
	}