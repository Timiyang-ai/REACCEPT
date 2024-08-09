@Authorized( { PrivilegeConstants.GET_CONCEPT_REFERENCE_TERMS })
	public List<ConceptReferenceTerm> getConceptReferenceTerms(String query, ConceptSource conceptSource, Integer start,
	        Integer length, boolean includeRetired) throws APIException;