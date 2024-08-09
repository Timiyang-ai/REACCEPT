@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PERSON_ATTRIBUTE_TYPES })
	public List<PersonAttributeType> getAllPersonAttributeTypes() throws APIException;