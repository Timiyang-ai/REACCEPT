@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_USERS })
	public List<User> getUsers(String nameSearch, List<Role> roles, boolean includeVoided) throws APIException;