@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_VISITS)
	public Visit getVisitByUuid(String uuid) throws APIException;