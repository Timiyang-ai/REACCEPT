@Transactional(readOnly = true)
	List<VisitType> getVisitTypes(String fuzzySearchPhrase);