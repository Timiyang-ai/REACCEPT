@Transactional(readOnly = true)
	@Authorized({ PrivilegeConstants.VIEW_VISIT_TYPES })
	List<VisitType> getAllVisitTypes();