@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_VISIT_ATTRIBUTE_TYPES)
	VisitAttributeType getVisitAttributeTypeByUuid(String uuid);