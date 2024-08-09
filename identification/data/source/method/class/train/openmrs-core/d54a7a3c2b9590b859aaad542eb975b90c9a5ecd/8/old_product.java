@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_VISIT_ATTRIBUTE_TYPES)
	List<VisitAttributeType> getAllVisitAttributeTypes();