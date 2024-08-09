@Transactional(readOnly=true)
	public List<Obs> getObservations(Cohort patients, List<Concept> concepts, Date fromDate, Date toDate);