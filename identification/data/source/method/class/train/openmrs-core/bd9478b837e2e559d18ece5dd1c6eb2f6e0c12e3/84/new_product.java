@Authorized( { PrivilegeConstants.VIEW_USERS })
	public List<User> getUsers(String name, List<Role> roles, boolean includeRetired, Integer start, Integer length)
	        throws APIException;