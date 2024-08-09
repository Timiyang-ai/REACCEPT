public List<Concept> getConcepts(String sortBy, String dir) {
		return getConceptDAO().getConcepts(sortBy, dir);
	}