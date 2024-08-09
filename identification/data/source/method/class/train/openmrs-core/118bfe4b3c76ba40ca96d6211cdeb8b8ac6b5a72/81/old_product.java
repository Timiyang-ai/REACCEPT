@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Concept getConceptByUuid(String uuid);