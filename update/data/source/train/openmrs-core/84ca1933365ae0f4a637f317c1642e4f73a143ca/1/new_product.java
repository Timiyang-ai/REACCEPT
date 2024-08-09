@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_USERS })
	public List<User> getAllUsers(List<Role> roles, boolean includeVoided) throws APIException;