@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PERSON_ATTRIBUTE_TYPES })
	public List<PersonAttributeType> getPersonAttributeTypes(String personType, String viewType) throws APIException;