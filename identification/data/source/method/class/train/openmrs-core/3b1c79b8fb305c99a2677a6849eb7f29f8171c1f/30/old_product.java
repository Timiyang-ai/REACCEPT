@Transactional(readOnly = true)
	public Role getRoleByUuid(String uuid) throws APIException;