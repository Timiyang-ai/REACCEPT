@Deprecated
    public List<ConceptWord> findConceptAnswers(String phrase, Locale locale, Concept concept, boolean includeRetired) {
		
		return getConceptAnswers(phrase, locale, concept);
	}