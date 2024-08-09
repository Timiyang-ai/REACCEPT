@Transactional(readOnly=true)
	public Collection<Encounter> getEncounters(Location loc, Date fromDate, Date toDate);