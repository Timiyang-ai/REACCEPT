@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PERSON_ATTRIBUTE_TYPES })
	public PersonAttribute getPersonAttribute(Integer id) throws APIException;