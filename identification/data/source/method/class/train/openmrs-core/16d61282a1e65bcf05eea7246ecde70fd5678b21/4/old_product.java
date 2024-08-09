@Transactional(readOnly=true)
	@Authorized({"View Concepts"})
	public Concept getConceptByName(String name);