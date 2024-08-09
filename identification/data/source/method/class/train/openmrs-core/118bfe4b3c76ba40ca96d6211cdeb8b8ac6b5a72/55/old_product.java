@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_USERS })
	public User getUserByUuid(String uuid) throws APIException;