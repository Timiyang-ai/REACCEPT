@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PERSON_ATTRIBUTE_TYPES })
	public PersonAttributeType getPersonAttributeType(Integer typeId) throws APIException;