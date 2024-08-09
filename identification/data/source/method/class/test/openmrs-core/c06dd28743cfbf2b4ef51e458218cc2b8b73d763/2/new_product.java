@Transactional(readOnly = true)
	@Authorized({ PrivilegeConstants.VIEW_VISIT_TYPES })
	VisitType getVisitType(Integer visitTypeId);