@Transactional(readOnly = true)
	public Privilege getPrivilege(String p) throws APIException;