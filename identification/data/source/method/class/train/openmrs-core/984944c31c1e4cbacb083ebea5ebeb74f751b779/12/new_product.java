@Deprecated
	@Transactional(readOnly = true)
	// this has anonymous access because its cached into generic js files
	public List<PersonAttributeType> getPersonAttributeTypes(String personType, String viewType) throws APIException;