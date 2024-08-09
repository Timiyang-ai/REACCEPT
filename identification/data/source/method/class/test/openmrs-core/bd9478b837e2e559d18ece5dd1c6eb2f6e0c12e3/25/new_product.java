@Transactional(readOnly=true)
	public Role getRole(String r) throws APIException;