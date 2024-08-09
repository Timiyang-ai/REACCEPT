@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PERSONS })
	public Person getPerson(User user) throws APIException;