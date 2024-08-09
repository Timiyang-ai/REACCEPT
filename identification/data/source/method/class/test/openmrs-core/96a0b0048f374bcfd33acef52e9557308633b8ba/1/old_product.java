@Authorized(requireAll = true, value = { PrivilegeConstants.ADD_VISITS, PrivilegeConstants.EDIT_VISITS })
	public Visit saveVisit(Visit visit) throws APIException;