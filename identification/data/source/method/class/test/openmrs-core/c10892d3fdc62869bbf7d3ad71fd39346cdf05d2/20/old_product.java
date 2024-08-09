@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_USERS })
	public List<User> getUsersByPerson(Person person, boolean includeRetired) throws APIException;