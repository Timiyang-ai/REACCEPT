@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_VISIT_ATTRIBUTE_TYPES)
	VisitAttributeType getVisitAttributeType(Integer id);