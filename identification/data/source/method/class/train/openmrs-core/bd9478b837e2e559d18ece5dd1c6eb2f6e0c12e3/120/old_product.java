@Transactional(readOnly=true)
	Privilege getPrivilege(String p) throws APIException;