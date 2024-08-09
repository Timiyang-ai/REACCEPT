@Transactional(readOnly=true)
	@Authorized({"View Users"})
	User getUserByUsername(String username) throws APIException;