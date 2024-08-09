@Transactional(readOnly = true)
	public String getGlobalProperty(String propertyName) throws APIException;