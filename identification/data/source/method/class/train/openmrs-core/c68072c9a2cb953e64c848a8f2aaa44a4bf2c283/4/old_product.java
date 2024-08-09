public List<Concept> findPossibleValues(String searchText) {
		List<Concept> concepts = new Vector<Concept>();
		try {
			for (ConceptWord word : Context.getConceptService().findConcepts(searchText, Context.getLocale(), false)) {
				concepts.add(word.getConcept());
			}
		}
		catch (Exception e) {
			// pass
		}
		return concepts;
	}