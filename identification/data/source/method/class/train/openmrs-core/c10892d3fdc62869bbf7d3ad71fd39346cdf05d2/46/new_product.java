@Authorized( { PrivilegeConstants.VIEW_USERS })
	public List<User> getUsersByName(String givenName, String familyName, boolean includeRetired) throws APIException;