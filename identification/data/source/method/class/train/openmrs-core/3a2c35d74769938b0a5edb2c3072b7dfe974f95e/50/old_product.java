@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_USERS })
	public boolean hasDuplicateUsername(User user) throws APIException;