@Authorized( { PrivilegeConstants.VIEW_PROGRAMS })
	@Transactional(readOnly = true)
	public Program getProgramByName(String name) throws APIException;