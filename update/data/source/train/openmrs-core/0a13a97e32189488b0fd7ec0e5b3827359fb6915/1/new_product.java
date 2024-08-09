@Authorized(PrivilegeConstants.GET_CONCEPTS)
	public List<Drug> getDrugs(String drugName, Concept concept, boolean searchKeywords, boolean searchDrugConceptNames,
	        boolean includeRetired, Integer start, Integer length) throws APIException;