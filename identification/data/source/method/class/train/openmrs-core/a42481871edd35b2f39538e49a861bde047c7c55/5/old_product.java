@Transactional(readOnly=true)
	@Authorized({"View Users"})
	List<User> getAllUsers(List<Role> roles, boolean includeVoided);