@Override
	public List<ConceptSearchResult> getConcepts(String phrase, List<Locale> locales, boolean includeRetired,
	                                             List<ConceptClass> requireClasses, List<ConceptClass> excludeClasses,
	                                             List<ConceptDatatype> requireDatatypes,
	                                             List<ConceptDatatype> excludeDatatypes, Concept answersToConcept,
	                                             Integer start, Integer size) throws APIException {
		
		if (requireClasses == null)
			requireClasses = new Vector<ConceptClass>();
		if (excludeClasses == null)
			excludeClasses = new Vector<ConceptClass>();
		if (requireDatatypes == null)
			requireDatatypes = new Vector<ConceptDatatype>();
		if (excludeDatatypes == null)
			excludeDatatypes = new Vector<ConceptDatatype>();
		
		return dao.getConcepts(phrase, locales, includeRetired, requireClasses, excludeClasses, requireDatatypes,
		    excludeDatatypes, answersToConcept, start, size);
		
	}