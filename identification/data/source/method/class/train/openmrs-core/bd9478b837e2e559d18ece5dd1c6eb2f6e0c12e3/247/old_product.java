@Transactional(readOnly=true)
	@Authorized({"View Users"})
	public List<User> getUsersByRole(Role role) throws APIException;