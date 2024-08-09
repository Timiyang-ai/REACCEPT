@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PERSON_ATTRIBUTE_TYPES })
	public List<PersonAttributeType> getPersonAttributeTypes(String personType, String viewType) throws APIException;