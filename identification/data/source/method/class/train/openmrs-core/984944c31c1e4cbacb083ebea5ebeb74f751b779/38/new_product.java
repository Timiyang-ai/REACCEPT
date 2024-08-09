@Transactional(readOnly = true)
	// this has anonymous access because its cached into generic js files
	public List<PersonAttributeType> getPersonAttributeTypes(PERSON_TYPE personType, ATTR_VIEW_TYPE viewType)
	                                                                                                         throws APIException;