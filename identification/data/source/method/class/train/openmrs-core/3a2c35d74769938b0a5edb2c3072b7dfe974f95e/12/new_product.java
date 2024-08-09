@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Drug> getDrugs(Concept concept) throws APIException;