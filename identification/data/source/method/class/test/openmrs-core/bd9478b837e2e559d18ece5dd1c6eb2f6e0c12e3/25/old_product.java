@Transactional(readOnly=true)
	Role getRole(String r) throws APIException;