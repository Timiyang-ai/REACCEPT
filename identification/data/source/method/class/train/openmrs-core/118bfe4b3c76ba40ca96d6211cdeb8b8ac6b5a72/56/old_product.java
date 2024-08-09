@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PERSONS })
	public PersonName getPersonNameByUuid(String uuid) throws APIException;