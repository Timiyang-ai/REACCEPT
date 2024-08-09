@Transactional(readOnly=true)
	@Authorized({OpenmrsConstants.PRIV_VIEW_USERS})
	public List<User> getUsersByRole(Role role) throws APIException;