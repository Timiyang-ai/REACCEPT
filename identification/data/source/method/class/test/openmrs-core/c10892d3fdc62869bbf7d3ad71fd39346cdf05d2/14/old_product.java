@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_USERS })
	public List<User> getUsersByPerson(Person person, boolean includeRetired) throws APIException;