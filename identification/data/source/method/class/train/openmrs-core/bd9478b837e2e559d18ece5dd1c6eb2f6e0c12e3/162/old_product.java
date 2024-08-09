@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_USERS })
	public List<User> getUsers() throws APIException;