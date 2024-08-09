@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_USERS })
	public User getUserByUsername(String username) throws APIException;