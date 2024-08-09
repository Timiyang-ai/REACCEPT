@Transactional(readOnly=true)
	public List<Drug> getDrugs(Concept concept);