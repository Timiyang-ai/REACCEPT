@Transactional(readOnly=true)
	@Authorized({"View Concepts"})
	public List<Drug> getDrugs(Concept concept);