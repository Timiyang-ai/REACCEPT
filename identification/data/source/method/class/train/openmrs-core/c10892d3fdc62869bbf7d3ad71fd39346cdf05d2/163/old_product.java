@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_USERS })
	public List<User> getUsersByName(String givenName, String familyName, boolean includeVoided) throws APIException;