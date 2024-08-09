@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.GET_ORDERS)
	public List<Order> getOrderHistoryByConcept(Patient patient, Concept concept);