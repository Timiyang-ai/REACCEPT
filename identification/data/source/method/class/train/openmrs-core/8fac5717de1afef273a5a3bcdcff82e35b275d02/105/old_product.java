public List<ConceptWord> findConceptAnswers(String phrase, Locale locale,
			Concept concept, boolean includeRetired) {
		List<ConceptWord> conceptWords = getConceptDAO().findConceptAnswers(
				phrase, locale, concept, includeRetired);

		return weightWords(phrase, locale, conceptWords);

	}