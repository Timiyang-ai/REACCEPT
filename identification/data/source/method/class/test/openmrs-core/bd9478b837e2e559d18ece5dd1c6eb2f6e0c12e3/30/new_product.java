@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_USERS })
	public List<User> getUsersByRole(Role role) throws APIException;