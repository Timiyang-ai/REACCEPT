@Deprecated
	@Authorized( { PrivilegeConstants.VIEW_PERSONS })
	public Person getPerson(Patient pat) throws APIException;