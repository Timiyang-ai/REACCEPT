@Authorized(PrivilegeConstants.VIEW_ORDERS)
	public List<Order> getOrders(OrderType orderType, List<Patient> patients, List<Concept> concepts, List<User> orderers,
	        List<Encounter> encounters);