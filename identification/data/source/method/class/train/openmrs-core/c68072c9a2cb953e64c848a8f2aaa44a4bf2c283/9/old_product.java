@Override
	public List<Concept> findPossibleValues(String searchText) {
		List<Concept> concepts = new ArrayList<Concept>();
		try {
			
			for (ConceptSearchResult searchResult : Context.getConceptService().getConcepts(searchText,
			    Collections.singletonList(Context.getLocale()), false, null, null, null, null, null, null, null)) {
				concepts.add(searchResult.getConcept());
			}
		}
		catch (Exception e) {
			// pass
		}
		return concepts;
	}