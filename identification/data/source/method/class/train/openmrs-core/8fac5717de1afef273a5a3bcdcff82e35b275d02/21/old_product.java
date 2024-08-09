@Transactional(readOnly=true)
	@Authorized({"View Concepts"})
	public List<Concept> getConceptsByClass(ConceptClass cc);