@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getConceptsByConceptSet(Concept concept) throws APIException;