@Authorized( { PrivilegeConstants.GET_USERS })
	public List<User> getUsersByPerson(Person person, boolean includeRetired) throws APIException;