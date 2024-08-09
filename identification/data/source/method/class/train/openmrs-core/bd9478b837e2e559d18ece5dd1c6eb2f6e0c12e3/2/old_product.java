@Transactional(readOnly=true)
	@Authorized({"View Users"})
	List<User> getUsers() throws APIException;