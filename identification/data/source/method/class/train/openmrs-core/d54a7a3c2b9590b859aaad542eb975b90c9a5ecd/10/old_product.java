@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_VISITS)
	VisitAttribute getVisitAttributeByUuid(String uuid);