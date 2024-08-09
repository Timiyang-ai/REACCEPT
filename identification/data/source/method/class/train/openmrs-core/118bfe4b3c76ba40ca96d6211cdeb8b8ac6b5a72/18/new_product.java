@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PERSONS })
	public PersonAttribute getPersonAttributeByUuid(String uuid) throws APIException;