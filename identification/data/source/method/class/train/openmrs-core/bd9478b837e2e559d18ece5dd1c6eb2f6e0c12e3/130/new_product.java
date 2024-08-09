@Transactional(readOnly = true)
	public List<Obs> getObservations(Concept question, String sort, Integer personType);