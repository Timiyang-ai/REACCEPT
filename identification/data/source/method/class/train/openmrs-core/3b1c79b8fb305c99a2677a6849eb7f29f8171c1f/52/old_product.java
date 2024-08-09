@Transactional(readOnly = true)
	public Privilege getPrivilegeByUuid(String uuid) throws APIException;