@Override
	public List<ConceptSearchResult> getConcepts(String phrase, List<Locale> locales, boolean includeRetired,
	                                             List<ConceptClass> requireClasses, List<ConceptClass> excludeClasses,
	                                             List<ConceptDatatype> requireDatatypes,
	                                             List<ConceptDatatype> excludeDatatypes, Concept answersToConcept,
	                                             Integer start, Integer size) throws APIException {
		//TODO fix DAO layer to filter well and return only concept words associated
		//to only unique concept for hibernate paging to work and avoid returning sublist
		List<ConceptWord> conceptWords = getConceptWords(phrase, locales, includeRetired, requireClasses, excludeClasses,
		    requireDatatypes, excludeDatatypes, answersToConcept, null, null);
		List<ConceptSearchResult> results = createSearchResultsList(conceptWords);
		int count = results.size();
		if (start == null && size == null)
			return results;
		
		if (start == null || start < 0)
			start = 0;
		if (size == null || size < 0)
			size = count;
		
		if (count == 0 || (start > (count - 1)) || size < 1)
			return Collections.emptyList();
		
		int lastIndex = start + size;
		if (lastIndex < count)
			return results.subList(start, lastIndex);
		
		return results.subList(start, count);
	}