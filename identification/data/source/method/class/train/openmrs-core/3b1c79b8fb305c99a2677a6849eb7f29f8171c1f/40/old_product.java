@Transactional(readOnly = true)
	public List<Role> getAllRoles() throws APIException;