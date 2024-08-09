@Transactional(readOnly=true)
	@Authorized({"View Users"})
	public User getUserByUsername(String username) throws APIException;