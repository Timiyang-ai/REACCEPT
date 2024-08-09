@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PERSON_ATTRIBUTE_TYPES })
	public List<PersonAttributeType> getPersonAttributeTypes(String exactName, String format, Integer foreignKey,
	                                                         Boolean searchable) throws APIException;