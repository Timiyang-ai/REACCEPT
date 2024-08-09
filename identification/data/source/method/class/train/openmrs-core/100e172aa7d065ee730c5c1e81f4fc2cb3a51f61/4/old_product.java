@Deprecated
	@Transactional(readOnly = true)
	public Map<String, ComplexObsHandler> getHandlers() throws APIException;