@Transactional(readOnly=true)
	@Authorized({"View Users"})
	List<User> getUsersByRole(Role role) throws APIException;