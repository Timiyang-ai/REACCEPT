@Transactional(readOnly = true)
	public List<Privilege> getAllPrivileges() throws APIException;