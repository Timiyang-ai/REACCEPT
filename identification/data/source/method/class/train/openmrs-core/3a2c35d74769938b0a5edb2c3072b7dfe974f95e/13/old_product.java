@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_USERS })
	public boolean hasDuplicateUsername(User user) throws APIException;