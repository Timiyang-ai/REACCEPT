@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PERSONS })
	public Person getPersonByUuid(String uuid) throws APIException;