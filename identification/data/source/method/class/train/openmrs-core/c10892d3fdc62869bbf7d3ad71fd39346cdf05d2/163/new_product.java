@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_USERS })
	public List<User> getUsersByName(String givenName, String familyName, boolean includeVoided) throws APIException;