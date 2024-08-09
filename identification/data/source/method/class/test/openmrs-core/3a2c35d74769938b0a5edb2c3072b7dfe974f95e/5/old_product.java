@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Drug> getDrugs(String phrase) throws APIException;