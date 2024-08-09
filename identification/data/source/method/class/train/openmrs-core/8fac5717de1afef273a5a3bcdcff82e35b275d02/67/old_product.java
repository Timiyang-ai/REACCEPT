@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PERSONS })
	public Person getPerson(Integer personId) throws APIException;