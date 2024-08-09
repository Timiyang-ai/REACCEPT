@Transactional(readOnly = true)
	public ComplexObsHandler getHandler(String key) throws APIException;