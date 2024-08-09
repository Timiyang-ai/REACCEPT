public List<Concept> findPossibleValues(String searchText) {
		List<Concept> concepts = new Vector<Concept>();
		try {
			
			for (ConceptWord word : Context.getConceptService().getConceptWords(searchText,
			    Collections.singletonList(Context.getLocale()), false, null, null, null, null, null, null, null)) {
				concepts.add(word.getConcept());
			}
		}
		catch (Exception e) {
			// pass
		}
		return concepts;
	}