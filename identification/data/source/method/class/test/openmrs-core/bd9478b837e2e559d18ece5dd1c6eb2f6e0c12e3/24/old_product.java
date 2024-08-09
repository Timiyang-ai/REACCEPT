@Transactional(readOnly=true)
	@Authorized({"View Users"})
	public List<User> getUsers() throws APIException;