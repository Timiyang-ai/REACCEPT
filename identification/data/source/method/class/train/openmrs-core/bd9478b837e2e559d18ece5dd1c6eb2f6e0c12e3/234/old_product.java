@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_USERS })
	public User getUser(Integer userId) throws APIException;