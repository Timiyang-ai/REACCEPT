public List<Concept> getConceptsByName(String name) throws APIException {
		return getConcepts(name, Context.getLocale(), true, null, null);
	}