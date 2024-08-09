@Transactional(readOnly=true)
	public String getGlobalProperty(String propertyName, String defaultValue);