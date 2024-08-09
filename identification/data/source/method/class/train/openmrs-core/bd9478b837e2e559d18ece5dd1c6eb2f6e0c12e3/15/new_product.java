@Transactional(readOnly=true)
	public Collection<Encounter> getEncounters(Date fromDate, Date toDate);