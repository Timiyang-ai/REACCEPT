@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPTS)
	public ConceptComplex getConceptComplex(Integer conceptId);