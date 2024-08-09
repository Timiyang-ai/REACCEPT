@Deprecated
    @Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getConcepts(String sortBy, String dir) throws APIException;