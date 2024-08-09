@Transactional(readOnly = true)
	public List<Obs> getObservations(List<Concept> concepts, Date fromDate, Date toDate);