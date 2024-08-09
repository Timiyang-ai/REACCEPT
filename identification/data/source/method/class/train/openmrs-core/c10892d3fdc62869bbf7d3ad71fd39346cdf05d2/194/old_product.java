@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getConceptsByAnswer(Concept concept) throws APIException;