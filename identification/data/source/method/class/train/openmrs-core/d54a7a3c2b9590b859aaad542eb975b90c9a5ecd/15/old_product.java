@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_VISITS)
	public List<Visit> getAllVisits() throws APIException;