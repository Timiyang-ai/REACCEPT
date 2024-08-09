public List<Concept> getConcepts(String sortBy, String dir) throws APIException{
		boolean asc = true ? dir.equals("asc") : !dir.equals("asc");
		return getAllConcepts(sortBy, asc, true);
	}