@Override
	public List<ConceptSearchResult> getConcepts(String phrase, Locale locale) throws APIException {
		List<ConceptWord> conceptWords = getConceptWords(phrase, locale);
		
		return createSearchResultsList(conceptWords);
	}