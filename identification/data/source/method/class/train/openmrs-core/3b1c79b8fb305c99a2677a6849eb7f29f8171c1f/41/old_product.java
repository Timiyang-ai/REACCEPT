@Transactional(readOnly = true)
	public GlobalProperty getGlobalPropertyByUuid(String uuid) throws APIException;