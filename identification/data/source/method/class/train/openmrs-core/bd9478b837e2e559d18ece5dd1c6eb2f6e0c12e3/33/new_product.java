@Authorized( { PrivilegeConstants.GET_USERS })
	public List<User> getUsersByRole(Role role) throws APIException;