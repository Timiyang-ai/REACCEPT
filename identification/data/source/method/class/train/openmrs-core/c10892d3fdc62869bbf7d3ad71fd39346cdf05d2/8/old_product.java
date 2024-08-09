@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getConceptsByMapping(String conceptCode, String mappingCode) throws APIException;