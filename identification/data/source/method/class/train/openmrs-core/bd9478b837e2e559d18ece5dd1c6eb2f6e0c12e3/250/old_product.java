@Transactional(readOnly = true)
	public Privilege getPrivilege(String privilege) throws APIException;