@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_USERS })
	public List<User> getAllUsers(List<Role> roles, boolean includeVoided) throws APIException;