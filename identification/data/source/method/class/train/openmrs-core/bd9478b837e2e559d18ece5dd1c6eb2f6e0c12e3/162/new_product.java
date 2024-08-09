@Deprecated
	@Authorized( { PrivilegeConstants.VIEW_USERS })
	public List<User> getUsers() throws APIException;