@Override
	public List<ConceptSearchResult> getConcepts(String phrase, List<Locale> locales, boolean includeRetired,
	                                             List<ConceptClass> requireClasses, List<ConceptClass> excludeClasses,
	                                             List<ConceptDatatype> requireDatatypes,
	                                             List<ConceptDatatype> excludeDatatypes, Concept answersToConcept,
	                                             Integer start, Integer size) throws APIException {
		
		List<ConceptWord> conceptWords = getConceptWords(phrase, locales, includeRetired, requireClasses, excludeClasses,
		    requireDatatypes, excludeDatatypes, answersToConcept, start, size);
		
		return createSearchResultsList(conceptWords);
	}