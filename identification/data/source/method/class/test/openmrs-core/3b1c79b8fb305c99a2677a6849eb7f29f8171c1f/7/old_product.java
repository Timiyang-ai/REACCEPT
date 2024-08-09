@Authorized(PrivilegeConstants.VIEW_CONCEPT_REFERENCE_TERMS)
	public Integer getCountOfConceptReferenceTerms(String query, ConceptSource conceptSource, boolean includeRetired)
	        throws APIException;