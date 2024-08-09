@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getConceptsByName(String name) throws APIException;