@Deprecated
	@Authorized(PrivilegeConstants.GET_CONCEPTS)
	public List<Drug> getDrugs(Concept concept) throws APIException;