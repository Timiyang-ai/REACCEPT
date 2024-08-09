@Transactional(readOnly=true)
	@Authorized({"View Users"})
	public boolean hasDuplicateUsername(User user) throws APIException;